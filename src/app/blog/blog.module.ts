import {CommonModule, NgOptimizedImage} from '@angular/common';
import {NgModule} from '@angular/core';

import {FormsModule} from '@angular/forms';
import {CKEditorModule} from '@ckeditor/ckeditor5-angular';
import {BlogContainerComponent} from './blog-container/blog.component';
import {BlogIndexComponent} from './blog-index/blog-index.component';
import {BlogPageComponent} from './blog-page/blog-page.component';
import {BlogRoutingModule} from './blog-routing.module';
import {BlogSummaryComponent} from './blog-summary/blog-summary.component';
import {CreateBlogComponent} from './create-blog/create-blog.component';

@NgModule({
  declarations: [
    BlogContainerComponent,
    BlogIndexComponent,
    BlogPageComponent,
    BlogSummaryComponent,
    CreateBlogComponent,
  ],
  imports: [
    CommonModule,
    BlogRoutingModule,
    CKEditorModule,
    FormsModule,
    NgOptimizedImage,
  ],

  providers: [],
})
export class BlogModule {
}
