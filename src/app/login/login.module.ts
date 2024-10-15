import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {ReactiveFormsModule} from '@angular/forms';
import {MatListModule} from '@angular/material/list';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './login.component';

const routes: Routes = [

  {path: '', component: LoginComponent},
];

@NgModule({
  declarations: [
    LoginComponent,
  ],
  exports: [RouterModule],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule.forChild(routes),
    MatListModule,
  ],
})
export class LoginModule {
}
