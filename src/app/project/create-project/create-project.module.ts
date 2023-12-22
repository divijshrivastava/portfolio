import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';

import {CreateProjectRoutingModule} from './create-project-routing.module';
import {CreateProjectComponent} from './create-project.component';

@NgModule({
  declarations: [
    CreateProjectComponent
  ],
  imports: [
    CommonModule,
    CreateProjectRoutingModule,
    FormsModule
  ]
})
export class CreateProjectModule {
}
