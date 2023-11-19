import {AfterViewInit, Component, OnInit, ViewEncapsulation} from '@angular/core';
import {DomSanitizer, Meta, Title} from '@angular/platform-browser';
import {ActivatedRoute, Router} from '@angular/router';
// import {ChangeEvent} from '@ckeditor/ckeditor5-angular';
import * as Editor from 'ckeditor5/build/ckeditor';
import {FetchServiceService} from 'src/app/services/fetch-service.service';
import {HighlightService} from 'src/app/services/highlight.service';
import {environment} from 'src/environments/environment';
import {UserService} from '../../services/user.service';
import {UtilService} from '../../services/util.service';

// import {ImageUploadAdapter} from '../create-blog/adapter/image-upload-adapter';

@Component({
  encapsulation: ViewEncapsulation.Emulated,
  selector: 'app-blog-page',
  styleUrls: ['./blog-page.component.scss'],
  templateUrl: './blog-page.component.html',
})
export class BlogPageComponent implements OnInit, AfterViewInit {

  public blogId!: string;
  public isAdmin = false;
  public canEdit = false;
  public blogHeading = ``;
  public publishedDate = 'Jan 24, 2020';
  public authorName = '';
  public timeToRead = '4 min read';
  public blogHeroImageSrc = ``;
  public authorImageSrc = ``;
  public blogHeroImageDetails = 'Image from Pexel.com';
  public blogContent: any = 'Loading...'; // = 'This is great world!';
  public blogSummary = '';
  public blogStatus = '';
  public isBlogPending = false;
  public blogTitleLink = '';
  public isBlogEditable = false;
  public Editor: any;
  public averageAdultReadingSpeed = 200;
  public lorem = 'Lorem <b>ipsum</b> dolor sit amet consectetur adipisicing elit. Tempore iste, nobis nesciunt voluptates saepe, nulla adipisci quas a minus rerum libero vitae vero<b> voluptatum </b>nam<br>,<h1> expedita </h1>harum magnam. Obcaecati, hic?';
  public config: any = {
    codeBlock: {
      languages: [
        {
          class: 'language-java java', label: 'Plain text', language: 'plaintext',
        },
      ],
    }
  };
  private highlighted = false;
  private data: string | undefined;

  constructor(private fetchService: FetchServiceService, private route: ActivatedRoute,
              private router: Router, private meta: Meta, private sanitizer: DomSanitizer,
              private highlightService: HighlightService, private userService: UserService,
              private utilService: UtilService, private title: Title) {
    this.Editor = Editor.Editor;
  }

  public ngOnInit(): void {
    console.log(this.route.snapshot.paramMap);
    this.blogTitleLink = this.route.snapshot.paramMap.get('link') || '';
    this.fetchService.get(`${environment.apiUrl}/blog/link/${this.blogTitleLink}`).subscribe((resp: any) => {
      console.log('Inside fetch blog call');
      this.blogHeading = resp.blogHeading;
      this.meta.updateTag({property: 'og:title', content: resp.blogHeading});
      this.utilService.updateTitle(resp.blogHeading);
      this.meta.updateTag({
        property: 'og:image',
        content: `${window.location.origin}${resp.blogImageSrc}`,
      });
      this.meta.updateTag({property: 'og:url', content: `${window.location.href}`});
      this.meta.updateTag({property: 'og:type', content: 'website'});
      this.meta.updateTag({property: 'og:site_name', content: 'Divij.tech'});
      this.meta.updateTag({name: 'description', content: resp.blogSummary});
      this.blogContent = resp.blogContent.replace(/src=\"/g, `src=\"${environment.apiUrl}`);
      this.blogContent = this.sanitizer.bypassSecurityTrustHtml(this.blogContent);
//      console.log(this.blogContent);
      this.authorName = resp.authorName;
      this.publishedDate = resp.publishedDate;
      this.timeToRead = resp.readTime;
      this.authorImageSrc = `${environment.apiUrl}${resp.authorImageSrc}`;
      this.blogHeroImageSrc = `${environment.apiUrl}${resp.blogImageSrc}`;
      this.blogSummary = resp.blogSummary;
      this.blogStatus = resp.blogStatus;
      this.isBlogPending = resp.blogStatus === 'PENDING';
      this.blogId = resp.id;
      this.highlightService.highlightAll();
      this.utilService.loader.next({state: 'off'});

    }, (error) => {
      if (error.status === 403) {
        this.navigateToErrorPageWithMessage('Sorry! You are not authorised!');
      } else if (error.status === 404) {
        this.navigateToErrorPageWithMessage('Sorry! Page not found.');
      }

    });

    this.userService.entitlements.subscribe((resp: string[]) => {
      this.isAdmin = resp.includes('ADMIN');
      if (this.isAdmin) {
        this.canEdit = true;
      } else {
        // tslint:disable-next-line:max-line-length
        this.canEdit = false; // TODO implement the edit blog flow: send blog id to backend and check if the logged in user is author or not, if she is, then return true, otherwise false and write logic here accordingly.
      }
    });

    this.highlighted = true;
    this.utilService.loader.next({state: 'on'});
  }

  public softDeleteBlog(): void {
    console.log(`Deleting Blog ${this.blogId}`);
    this.fetchService.delete(`${environment.apiUrl}/blog`, [this.blogId]).subscribe((res) => {
      if (res.responseCode === 'SUCCESS') {
        alert('Blog is deleted.');
      } else {
        alert('Sorry, could not delete blog.');
      }
      this.router.navigateByUrl('blog');
    });
  }

  public approveBlog(): void {
    console.log('Approving Blog.');
    this.fetchService.post(`${environment.apiUrl}/blog/approve`, this.blogId).subscribe((res) => {
      if (res.responseCode === 'SUCCESS') {
        alert('Blog is Approved');
      } else {
        alert('Sorry, could not approve blog.');
      }
      this.router.navigateByUrl('blog');
    });
  }

  public ngAfterViewInit() {
    console.log('Applying prismjs');
    this.highlightService.highlightAll();
    this.highlighted = true;
  }

  public editBlog() {
    this.isBlogEditable = true;
    console.log('Editing Blog');
  }

  /*
    public onChange(event: ChangeEvent): void {
      this.data = event.editor.getData();
      console.log(this.data);
      const plainText = (document.querySelector('ckeditor') as HTMLElement)?.innerText;
      this.timeToRead = this.calculateMinutesToRead(plainText);
    }*/
  /*
    public calculateMinutesToRead(plainText: string): string {

      const minutesToRead = Math.floor(plainText.replace(/\n/g, ' ').split(' ').length / (this.averageAdultReadingSpeed));
      return Number(minutesToRead).toString();
    }*/

  /*
    public onReady(editor: any): void {
      editor.plugins.get('FileRepository').createUploadAdapter = (loader: any) => {
        return new ImageUploadAdapter(loader, this.fetchService);
      };

      editor.ui.getEditableElement().parentElement.insertBefore(
        editor.ui.view.toolbar.element,
        editor.ui.getEditableElement(),
      );

    }*/

  private navigateToErrorPageWithMessage(message: string): void {
    this.router.navigate(['error'], {queryParams: {message}});
  }

  ngOnDestroy() {
    this.utilService.updateTitle('Divij');
  }

}
