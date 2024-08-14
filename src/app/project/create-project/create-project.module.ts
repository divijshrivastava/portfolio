import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {CreateProjectRoutingModule} from './create-project-routing.module';
import {CreateProjectComponent} from './create-project.component';
import {GithubCodeFormComponent} from './github-code-form/github-code-form.component';
import {WebsiteFormComponent} from './website-form/website-form.component';
import {YtvideoFormComponent} from './ytvideo-form/ytvideo-form.component';

@NgModule({
  declarations: [
    CreateProjectComponent,
    YtvideoFormComponent,
    WebsiteFormComponent,
    GithubCodeFormComponent
  ],
  imports: [
    CommonModule,
    CreateProjectRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class CreateProjectModule {
}
