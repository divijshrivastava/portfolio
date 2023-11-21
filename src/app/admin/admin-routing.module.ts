import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CanActivateTeam} from '../services/guard.service';
import {DashboardContainerComponent} from './dashboard-container/dashboard-container.component';
import {DashboardComponent} from './dashboard/dashboard.component';
import {InboxComponent} from './inbox/inbox.component';
import {SettingsComponent} from './settings/settings.component';
import {UploadDocsComponent} from './upload-docs/upload-docs.component';
import {UploadResumeComponent} from './upload-resume/upload-resume.component';
import {UserActivityComponent} from './user-activity/user-activity.component';

const routes: Routes = [
  {
    path: '', component: DashboardContainerComponent, canActivate: [CanActivateTeam],
    children: [
      {
        path: '', component: DashboardComponent, canActivate: [CanActivateTeam],
      },
      {
        path: 'user-activity', component: UserActivityComponent, canActivate: [CanActivateTeam],
      },
      {
        path: 'settings', component: SettingsComponent, canActivate: [CanActivateTeam],
      },
      {
        path: 'upload-docs', component: UploadDocsComponent, canActivate: [CanActivateTeam],
      },
      {
        path: 'upload-resume', component: UploadResumeComponent, canActivate: [CanActivateTeam],
      },
      {
        path: 'inbox', component: InboxComponent, canActivate: [CanActivateTeam],
      },
    ],
  },
];

@NgModule({
  exports: [RouterModule],
  imports: [RouterModule.forChild(routes)],
})
export class AdminRoutingModule {
}
