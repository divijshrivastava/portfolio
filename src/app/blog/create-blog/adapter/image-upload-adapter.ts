import {FetchServiceService} from "src/app/services/fetch-service.service";
import {environment} from "src/environments/environment";

export class ImageUploadAdapter {
  loader: any;
  xhr: any;

  constructor(loader: any, private fetchService: FetchServiceService) {
    this.loader = loader;
  }

  upload() {
    return this.loader.file
    .then((file: any) => new Promise((resolve, reject) => {
      this._initRequest();
      this._initListeners(resolve, reject, file);
      this._sendRequest(file);
    }));
  }

  abort() {
    if (this.xhr) {
      this.xhr.abort();
    }
  }

  _initRequest() {
    const xhr = this.xhr = new XMLHttpRequest();
    xhr.open('POST', environment.apiUrl + '/file/blog-image-upload', true); // TODO change the URL
    xhr.responseType = 'json';
    xhr.setRequestHeader("Accept", "application/json");
  }

  _initListeners(resolve: { (value: unknown): void; (arg0: { default: any; }): void; }, reject: {
    (reason?: any): void;
    (arg0: string | undefined): any;
  }, file: { name: any; }) {
    const xhr = this.xhr;
    const loader = this.loader;
    const genericErrorText = `Couldn't upload file: ${file.name}.`;
    xhr.addEventListener('error', () => reject(genericErrorText));
    xhr.addEventListener('abort', () => reject());
    xhr.addEventListener('load', () => {
      const response = xhr.response;
      if (!response || response.error) {
        return reject(response && response.error ? response.error.message : genericErrorText);
      }
      resolve({
        default: response.url
      });
    });
    if (xhr.upload) {
      xhr.upload.addEventListener('progress', (evt: {
        lengthComputable: any;
        total: any;
        loaded: any;
      }) => {
        if (evt.lengthComputable) {
          loader.uploadTotal = evt.total;
          loader.uploaded = evt.loaded;
        }
      });
    }
  }

  _sendRequest(file: string | Blob) {
    const data = new FormData();
    data.append('file', file);
    this.xhr.send(data);
  }
}

interface Loader {
  file: any;
}
