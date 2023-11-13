import {CommonModule} from '@angular/common';
import {NgModule, NO_ERRORS_SCHEMA} from '@angular/core';
import {PdfViewerModule} from 'ng2-pdf-viewer';
import {NgxExtendedPdfViewerModule} from 'ngx-extended-pdf-viewer';
import {ResumeRoutingModule} from './resume-routing.module';
import {ResumeComponent} from './resume.component';

@NgModule({
  declarations: [ResumeComponent],
  imports: [
    CommonModule,
    ResumeRoutingModule,
    PdfViewerModule,
    NgxExtendedPdfViewerModule],
  schemas: [NO_ERRORS_SCHEMA],
})
export class ResumeModule {
}
