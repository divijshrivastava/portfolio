import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {FetchServiceService} from 'src/app/services/fetch-service.service';
import {UserService} from 'src/app/services/user.service';
import {environment} from 'src/environments/environment';
import {UtilService} from '../../services/util.service';
import {BlogSummary} from '../blog-summary';

@Component({
  selector: 'app-blog-index',
  templateUrl: './blog-index.component.html',
  styleUrls: ['./blog-index.component.scss']
})
export class BlogIndexComponent implements OnInit {

  blogs: BlogSummary[] = [];// = new Array(4);
  showCreateButton: boolean = false;

  constructor(private router: Router, private fetchService: FetchServiceService,
              private userService: UserService, private activatedRoute: ActivatedRoute, private utilService: UtilService) {
  }

  ngOnInit(): void {

    this.authenticated();

    this.blogs = [];//new Array(7);
    let servicePath = `${environment.apiUrl}/blog/1/1`;

    this.fetchService.get(servicePath).subscribe(
      resp => {
        this.blogs = resp.message.map((item: any) => {
          return {
            authorName: item.authorName,
            synopsis: item.blogHeading,
            id: item.id
            ,
            blogDate: item.publishedDate,
            readTime: item.readTime,
            authorImageSrc: `${environment.apiUrl}${item.authorImageSrc}`,
            blogImageSrc: `${environment.apiUrl}${item.blogImageSrc}`,
            blogContent: item.blogContent,
            blogStatus: item.blogStatus,
            blogTitleLink: item.blogTitleLink
          };
        });
        this.utilService.loader.next({state: 'off'});

      }
    );
    this.utilService.loader.next({state: 'on'});

  }

  ngOnChanges() {

    this.authenticated();
  }

  authenticated() {
    this.userService.entitlements.subscribe((resp: string[]) => {
      this.showCreateButton = resp.includes('AUTHOR');
    });
  }
}
