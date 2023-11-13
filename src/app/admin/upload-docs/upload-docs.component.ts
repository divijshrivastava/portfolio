import {Component} from '@angular/core';
import {environment} from '../../../environments/environment';
import {FetchServiceService} from '../../services/fetch-service.service';

@Component({
  selector: 'app-upload-docs',
  templateUrl: './upload-docs.component.html',
  styleUrls: ['./upload-docs.component.scss'],
})
export class UploadDocsComponent {

  public response: any = {message: '', responseMessage: ''};
  public document: File | undefined;
  public fileLink = '';
  public fileLinkShow = false;
  public showResponse = false;

  constructor(private fetchService: FetchServiceService) {

  }

  public readURL(input: any): void {

    let files: any;
    files = input!.currentTarget!.files;
    const document: File = files[0];

    if (files && document) {
      this.document = document;
    }
  }

  public uploadDocument(): void {

    const formData = new FormData();
    formData.append('file', this.document as Blob, this.document == null ? '' : this.document.name);
    console.log(`${environment.serverUrl}`);
    this.fetchService.post(environment.apiUrl + '/file/create', formData).subscribe(
      (resp) => {
        this.showResponse = true;
        this.response.message = resp.message;
        this.response.responseMessage = resp.responseMessage;
        if (resp.message) {
          this.fileLinkShow = true;
          this.fileLink = `${environment.serverUrl}/file/${resp.message}`;
        }
      },
    );
  }
}
