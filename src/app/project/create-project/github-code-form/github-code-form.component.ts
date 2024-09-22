import {Component} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {Observable} from 'rxjs';
import {concatMap} from 'rxjs/operators';
import {environment} from '../../../../environments/environment';
import {FetchServiceService} from '../../../services/fetch-service.service';

@Component({
  selector: 'app-github-code-form',
  templateUrl: './github-code-form.component.html',
  styleUrls: ['./github-code-form.component.scss']
})
export class GithubCodeFormComponent {

  isImage = false;
  isProjectLiveLink: boolean = false;

  codeProjectForm = this.formBuilder.group({
    heading: ['', Validators.required],
    description: ['', Validators.required],
    link: ['', Validators.required],
    imageId: [''],
    isDeploymentLinkPresent: [''],
    deploymentLink: [''],
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
    if (this.isImage) {
      console.log(this.codeProjectForm);
      const formData = new FormData();
      formData.append('file', this.image as Blob, this.image == null ? '' : this.image.name);
      const imageCall: Observable<any> = this.fetchService.post(environment.apiUrl + '/file/create', formData);
      const formRequest = (imageId: any) => {
        const requestBody = {
          projectWrapper: {
            codeLink: this.codeProjectForm.get('link')?.value,
            isImagePresent: this.isImage,
            imageId: imageId.message,
            heading: this.codeProjectForm.get('heading')?.value,
            description: this.codeProjectForm.get('description')?.value,
            projectType: 'CODE',
            isDeploymentLinkPresent: this.isProjectLiveLink,
            deploymentLink: this.codeProjectForm.get('deploymentLink')?.value,
          },
        };
        return this.fetchService.post(`${environment.apiUrl}/project`, requestBody);
      };

      imageCall.pipe(concatMap(formRequest)).subscribe((resp) => {
        alert('Project Saved with ID: ' + resp.projectId);
        this.codeProjectForm.reset();
      });

    } else {
      this.fetchService.post(`${environment.apiUrl}/project`, {
        projectWrapper: {
          codeLink: this.codeProjectForm.get('link')?.value,
          isImagePresent: this.isImage,
          heading: this.codeProjectForm.get('heading')?.value,
          description: this.codeProjectForm.get('description')?.value,
          projectType: 'CODE',
          isDeploymentLinkPresent: this.isProjectLiveLink,
          deploymentLink: this.codeProjectForm.get('deploymentLink')?.value,
        },
      }).subscribe((resp) => {
        alert('Project Saved with ID: ' + resp.projectId);
        this.codeProjectForm.reset();
      });
    }
  }

}
