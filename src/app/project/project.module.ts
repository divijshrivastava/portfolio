import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {ProjectCardComponent} from './project-card/project-card.component';

import {ProjectRoutingModule} from './project-routing.module';
import {ProjectComponent} from './project.component';

@NgModule({
  declarations: [
    ProjectComponent,
    ProjectCardComponent
  ],
  imports: [
    CommonModule,
    ProjectRoutingModule
  ]
})
export class ProjectModule {
}
