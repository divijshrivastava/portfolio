import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {ProjectCardComponent} from './project-card/project-card.component';
import {ProjectContainerComponent} from './project-container/project-container.component';
import {ProjectComponent} from './project-index/project.component';

import {ProjectRoutingModule} from './project-routing.module';

@NgModule({
  declarations: [
    ProjectComponent,
    ProjectCardComponent,
    ProjectContainerComponent
  ],
  imports: [
    CommonModule,
    ProjectRoutingModule
  ]
})
export class ProjectModule {
}
