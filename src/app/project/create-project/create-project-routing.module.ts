import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CanActivateTeam} from '../../services/guard.service';
import {CreateProjectComponent} from './create-project.component';

const routes: Routes = [{
  path: '', component: CreateProjectComponent,
  canActivate: [CanActivateTeam]
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CreateProjectRoutingModule {
}
