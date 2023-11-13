import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ErrorComponent} from './error/error/error.component';
import {HomeComponent} from './home/home.component';

const routes: Routes = [

  {path: '', component: HomeComponent},
  {
    path: 'blogs',
    loadChildren: () => import('./blog/blog.module')
    .then(m => m.BlogModule)
  }
  ,
  {
    path: 'projects',
    loadChildren: () => import('./project/project.module')
    .then((m) => m.ProjectModule),
  },
  {
    path: 'admin',
    loadChildren: () => import('./admin/admin.module')
    .then((m) => m.AdminModule),
  },
  {
    path: 'resume',
    loadChildren: () => import('./resume/resume.module').then(m => m.ResumeModule)
  },
  {path: '**', pathMatch: 'full', component: ErrorComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {anchorScrolling: 'enabled'})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
