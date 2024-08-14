import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ProjectContainerComponent} from './project-container/project-container.component';
import {ProjectComponent} from './project-index/project.component';

const routes: Routes = [{
  path: '', component: ProjectContainerComponent, children: [{
    path: '', component: ProjectComponent
  }, {
    path: 'create', loadChildren: () => import('./create-project/create-project.module')
    .then((m) => m.CreateProjectModule),
  }],
},

];

@NgModule({imports: [RouterModule.forChild(routes)], exports: [RouterModule]})
export class ProjectRoutingModule {
}
