import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {BlogGuard} from '../../services/guard-blog.service';
import {CreateBlogComponent} from './create-blog.component';

const routes: Routes = [
  {
    path: '',
    component: CreateBlogComponent,
    canActivate: [BlogGuard]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CreateBlogRoutingModule {
}
