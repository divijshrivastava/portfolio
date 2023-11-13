import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {FetchServiceService} from 'src/app/services/fetch-service.service';
import {UserService} from 'src/app/services/user.service';
import {environment} from 'src/environments/environment';
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
              private userService: UserService, private activatedRoute: ActivatedRoute) {
  }

  ngOnInit(): void {

    this.authenticated();

    this.blogs = [];//new Array(7);
    let servicePath = `${environment.apiUrl}/blog/1/1`;

    this.fetchService.get(servicePath).subscribe(
      resp => {
        console.log("Fetching Blogs.");
        console.log(resp);
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

      }
    );

  }

  ngOnChanges() {

    this.authenticated();
  }

  goToBlog(id: String, blogTitleLink: string) {
    console.log("Going to blog ", id, blogTitleLink);
    this.router.navigate([`/app/blogs/${blogTitleLink}`], {
      relativeTo: this.activatedRoute,
      skipLocationChange: true
    });
    console.log("skipLocationChange: true");

  }

  authenticated() {
    console.log("Authenticating!")
    this.userService.isLoggedUser().subscribe((resp: any) => {
      this.showCreateButton = resp.message == null ? false : true;
    });
  }
}
