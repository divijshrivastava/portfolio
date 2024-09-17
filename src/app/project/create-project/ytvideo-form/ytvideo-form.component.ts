import {Component} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {Observable} from 'rxjs';
import {concatMap} from 'rxjs/operators';
import {environment} from '../../../../environments/environment';
import {FetchServiceService} from '../../../services/fetch-service.service';

@Component({
  selector: 'app-ytvideo-form',
  templateUrl: './ytvideo-form.component.html',
  styleUrls: ['./ytvideo-form.component.scss']
})
export class YtvideoFormComponent {

  isImage = false;
  public image: File | undefined;
  ytVideoForm = this.formBuilder.group({
    name: ['', Validators.required],
    description: ['', Validators.required],
    link: ['', Validators.required],
    imageId: [''],
  });

  constructor(private formBuilder: FormBuilder, private fetchService: FetchServiceService) {
  }

  ngSubmit() {
    console.log('submitting form!');
    if (this.isImage) {

      const formData = new FormData();
      formData.append('file', this.image as Blob, this.image == null ? '' : this.image.name);
      const imageCall: Observable<any> = this.fetchService.post(environment.apiUrl + '/file/create', formData);
      const formRequest = (imageId: any) => {
        const requestBody = {
          projectWrapper: {
            videoLink: this.ytVideoForm.get('link')?.value,
            isImagePresent: this.isImage,
            imageId: imageId.message,
            heading: this.ytVideoForm.get('heading')?.value,
            description: this.ytVideoForm.get('description')?.value,
            projectType: 'YTVIDEO',
          },
        };
        return this.fetchService.post(`${environment.apiUrl}/project`, requestBody);
      };

      imageCall.pipe(concatMap(formRequest)).subscribe((resp) => {
        alert('Project Saved with ID: ' + resp.projectId);
        this.ytVideoForm.reset();
      });

    } else {
      console.log('submitting form');
      console.log(this.ytVideoForm);
      this.fetchService.post(`${environment.apiUrl}/project`, {
        projectWrapper: {
          videoLink: this.ytVideoForm.get('link')?.value,
          isImagePresent: this.isImage,
          heading: this.ytVideoForm.get('name')?.value,
          description: this.ytVideoForm.get('description')?.value,
          projectType: 'YTVIDEO',
        },
      }).subscribe((resp) => {
        alert('Project Saved with ID: ' + resp.projectId);
        this.ytVideoForm.reset();
      });
    }
  }

  public onImagePicked(image: Event): void {
    const file = image.target as HTMLInputElement;
    if (!file || !file.files) {
      return;
    }
    this.image = file.files[0];
  }

}
