import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CanActivateTeam} from '../services/guard.service';
import {BlogContainerComponent} from './blog-container/blog.component';
import {BlogIndexComponent} from './blog-index/blog-index.component';
import {BlogPageComponent} from './blog-page/blog-page.component';
import {CreateBlogComponent} from './create-blog/create-blog.component';

const routes: Routes = [
  {
    path: '', component: BlogContainerComponent,
    children: [{
      path: '', component: BlogIndexComponent,
    }, {
      path: 'create',
      component: CreateBlogComponent,
      canActivate: [CanActivateTeam],
    },
      {
        path: ':link',
        component: BlogPageComponent,
      }],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class BlogRoutingModule {
}
