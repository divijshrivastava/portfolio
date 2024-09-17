import {Component} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {Observable} from 'rxjs';
import {concatMap} from 'rxjs/operators';
import {environment} from '../../../../environments/environment';
import {FetchServiceService} from '../../../services/fetch-service.service';

@Component({
  selector: 'app-website-form',
  templateUrl: './website-form.component.html',
  styleUrls: ['./website-form.component.scss']
})
export class WebsiteFormComponent {

  isImage = false;
  isCodeLink = false;
  isWebsiteLinkPresent = false;
  websiteProjectForm = this.formBuilder.group({
    name: ['', Validators.required],
    description: ['', Validators.required],
    link: [''],
    imageId: [''],
    codeLink: [''],
    isWebsiteLinkPresent: [''],
    websiteLink: [''],
  });

  public image: File | undefined;

  constructor(private formBuilder: FormBuilder, private fetchService: FetchServiceService) {
  }

  public onImagePicked(image: Event): void {
    const file = image.target as HTMLInputElement;
    if (!file || !file.files) {
      return;
    }
    this.image = file.files[0];
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
            websiteLink: this.isWebsiteLinkPresent ? this.websiteProjectForm.get('link')?.value : '',
            isWebsiteLinkPresent: this.isWebsiteLinkPresent,
            isCodeLinkPresent: this.isCodeLink,
            codeLink: this.isCodeLink ? this.websiteProjectForm.get('codeLink')?.value : '',
            imageId: imageId.message,
            heading: this.websiteProjectForm.get('heading')?.value,
            description: this.websiteProjectForm.get('description')?.value,
            projectType: 'WEBSITE',
          },
        };
        return this.fetchService.post(`${environment.apiUrl}/project`, requestBody);
      };

      imageCall.pipe(concatMap(formRequest)).subscribe((resp) => {
        alert('Project Saved with ID: ' + resp.projectId);
        this.websiteProjectForm.reset();
      });

    } else {
      console.log('submitting form');
      console.log(this.websiteProjectForm);
      this.fetchService.post(`${environment.apiUrl}/project`, {
        projectWrapper: {
          websiteLink: this.isWebsiteLinkPresent ? this.websiteProjectForm.get('link')?.value : '',
          isWebsiteLinkPresent: this.isWebsiteLinkPresent,
          isCodeLinkPresent: this.isCodeLink,
          codeLink: this.isCodeLink ? this.websiteProjectForm.get('codeLink')?.value : '',
          heading: this.websiteProjectForm.get('heading')?.value,
          description: this.websiteProjectForm.get('description')?.value,
          projectType: 'WEBSITE',
        },
      }).subscribe((resp) => {
        alert('Project Saved with ID: ' + resp.projectId);
        this.websiteProjectForm.reset();
      });
    }
  }

}
