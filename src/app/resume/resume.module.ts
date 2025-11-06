import {CommonModule} from '@angular/common';
import {NgModule, NO_ERRORS_SCHEMA} from '@angular/core';
import {ResumeRoutingModule} from './resume-routing.module';
import {ResumeComponent} from './resume.component';

@NgModule({
  declarations: [ResumeComponent],
  imports: [
    CommonModule,
    ResumeRoutingModule,
    ],
  schemas: [NO_ERRORS_SCHEMA],
})
export class ResumeModule {
}
