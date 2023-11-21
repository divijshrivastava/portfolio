import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {MatPaginatorModule} from '@angular/material/paginator';

import {AdminRoutingModule} from './admin-routing.module';
import {DashboardContainerComponent} from './dashboard-container/dashboard-container.component';
import {DashboardComponent} from './dashboard/dashboard.component';
import {InboxComponent} from './inbox/inbox.component';
import {SettingsComponent} from './settings/settings.component';
import {UploadDocsComponent} from './upload-docs/upload-docs.component';
import {UploadResumeComponent} from './upload-resume/upload-resume.component';
import {UserActivityComponent} from './user-activity/user-activity.component';

@NgModule({
  declarations: [
    UserActivityComponent,
    DashboardComponent,
    SettingsComponent,
    DashboardContainerComponent,
    UploadDocsComponent,
    UploadResumeComponent,
    InboxComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    MatPaginatorModule,
  ],
})
export class AdminModule {
}
