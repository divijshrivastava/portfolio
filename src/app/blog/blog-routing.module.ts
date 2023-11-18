import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {BlogContainerComponent} from './blog-container/blog.component';
import {BlogIndexComponent} from './blog-index/blog-index.component';

const routes: Routes = [
  {
    path: '', component: BlogContainerComponent,
    children: [{
      path: '', component: BlogIndexComponent,
    }, {
      path: 'create',
      loadChildren: () => import('./create-blog/create-blog.module').then((m) => m.CreateBlogModule),
    },
      {
        path: ':link',
        loadChildren: () => import('./blog-page/blog-page.module').then((m) => m.BlogPageModule),
      }],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class BlogRoutingModule {
}
