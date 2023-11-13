import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {MatPaginatorModule} from '@angular/material/paginator';

import {AdminRoutingModule} from './admin-routing.module';
import {UserActivityComponent} from './user-activity/user-activity.component';
import {DashboardComponent} from './dashboard/dashboard.component';
import {SettingsComponent} from './settings/settings.component';
import {DashboardContainerComponent} from './dashboard-container/dashboard-container.component';
import {UploadDocsComponent} from './upload-docs/upload-docs.component';
import {UploadResumeComponent} from './upload-resume/upload-resume.component';

@NgModule({
  declarations: [
    UserActivityComponent,
    DashboardComponent,
    SettingsComponent,
    DashboardContainerComponent,
    UploadDocsComponent,
    UploadResumeComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    MatPaginatorModule,
  ],
})
export class AdminModule {
}
