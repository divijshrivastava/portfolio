import {Component, Input, OnInit} from '@angular/core';
import {FetchServiceService} from 'src/app/services/fetch-service.service';
import {BlogSummary} from '../blog-summary';

@Component({
  selector: 'app-blog-summary',
  templateUrl: './blog-summary.component.html',
  styleUrls: ['./blog-summary.component.scss']
})
export class BlogSummaryComponent implements OnInit {

  @Input()
  data!: BlogSummary;// = new BlogSummary;

  synopsis = `Why is Angular better than React? This is why it is better.`;

  blogDate = "Jul 10, 2010";
  readTime = "4 min read";
  authorName = "Divij Shrivastava";
  authorImgSrc = "http://localhost:80/download/24";//"https://picsum.photos/200";
  blogImageSrc = "https://picsum.photos/200";
  blogContent!: string;
  isPending = false;
  blogTitleLink = "";

  constructor(private fetchService: FetchServiceService) {
  }

  ngOnChanges() {
    this.synopsis = this.data.synopsis;
    this.blogDate = this.data.blogDate;
    this.readTime = this.data.readTime;
    this.authorName = this.data.authorName;
    this.authorImgSrc = this.data.authorImageSrc;
    this.blogImageSrc = this.data.blogImageSrc;
    this.blogContent = this.data.blogContent;
    this.isPending = this.data.blogStatus === 'PENDING';
    this.blogTitleLink = this.data.blogTitleLink;
  }

  ngOnInit(): void {

    let pageIndex: number = 1;
    let items: number = 3;
    //  this.fetchService.get(`${environment.apiUrl}blogs/${items}/${pageIndex}`);

  }

}


