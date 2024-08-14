import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CreateProjectComponent} from './create-project.component';

const routes: Routes = [{
  path: '', component: CreateProjectComponent
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CreateProjectRoutingModule {
}
