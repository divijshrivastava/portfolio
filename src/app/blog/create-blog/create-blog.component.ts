import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {ChangeEvent} from '@ckeditor/ckeditor5-angular';
import * as Editor from 'ckeditor5/build/ckeditor';

import 'prismjs/components/prism-javascript';
import 'prismjs/components/prism-scss';
import 'prismjs/components/prism-yaml';
import {concatMap} from 'rxjs/operators';
import {FetchServiceService} from 'src/app/services/fetch-service.service';
import {environment} from 'src/environments/environment';
import {UtilService} from '../../services/util.service';
import {ImageUploadAdapter} from './adapter/image-upload-adapter';

@Component({
  selector: 'app-create-blog',
  styleUrls: ['./create-blog.component.scss'],
  templateUrl: './create-blog.component.html',
})
export class CreateBlogComponent implements OnInit {

  public Editor: any;

  public data = '';
  public blogHeading!: any;
  public blogDto: any = {};
  public averageAdultReadingSpeed = 200;
  public blogSummary = '';
  public config: any = {
    codeBlock: {
      languages: [
        {
          class: 'language-java java', label: 'Plain text', language: 'plaintext',
        },
      ],
    }
  };
//  public config:any = { 'codeBlock': { 'languages': [ { 'language': 'plaintext', 'label': 'Plain text', 'class': '' },
//  { 'language': 'php', 'label': 'PHP', 'class': 'php-code' },
//  { 'language': 'javascript', 'label': 'JavaScript', 'class': 'js javascript js-code' },{ 'language': 'python', 'label': 'Python' }]};

  public blogImage: File | null = null;
  public blogContent: string | null = null;
  public blogCoverId: string | null = null;
  public publishedDate!: string;
  public readTime!: string;
  public blogImageSrc!: string;
  public coverImage!: any;
  public authorImageSrc!: string;
  public minutesToRead: any;
  public lorem = 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Tempore iste, nobis nesciunt voluptates saepe, nulla adipisci quas a minus rerum libero vitae vero voluptatum nam, expedita harum magnam. Obcaecati, hic?';

  constructor(private fetchService: FetchServiceService, private router: Router, private utilService: UtilService) {
    this.Editor = Editor.Editor;
  }

  public uploadBlogImage() {
    const formData = new FormData();
    formData.append('file', this.blogImage as Blob, this.blogImage == null ? '' : this.blogImage.name);
    console.log(`${environment.serverUrl}`);
    return this.fetchService.post(environment.apiUrl + '/file/create', formData);

  }

  public fileUploaded(event: any): void {

    this.blogImage = (event.target.files[0] as File);

  }

  public readURL(input: any): void {

    let files: any;
    files = input!.currentTarget!.files;
    const image: File = files[0];
    // console.log(image);

    if (files && image) {
      const reader: FileReader = new FileReader();
      this.coverImage = (document.querySelectorAll('.cover_image_view')[0] as HTMLImageElement)
        || {} as Element;

      reader.onload = (e: Event) => {
        this.coverImage.src = reader.result as string;
      };

      reader.readAsDataURL(image);
      this.blogImage = image;
    }
  }

  public onReady(editor: any): void {
    editor.plugins.get('FileRepository').createUploadAdapter = (loader: any) => {
      return new ImageUploadAdapter(loader, this.fetchService);
    };

    editor.ui.getEditableElement().parentElement.insertBefore(
      editor.ui.view.toolbar.element,
      editor.ui.getEditableElement(),
    );

  }

  public publishBlog(): void {

    if (this.blogHeading && this.coverImage && this.data && this.blogSummary) {

      this.uploadBlogImage().pipe(
        concatMap((res) => {
            this.blogDto.blogHeading = this.blogHeading;
            this.blogDto.authorName = 'divij';
            this.blogDto.publishedDate = new Date().toString();
            this.blogDto.readTime = '2';
            this.blogDto.blogImageSrc = res.message;
            this.blogDto.authorImageSrc = '/file/28';
            this.blogDto.blogContent = this.data;
            this.blogDto.readTime = this.minutesToRead;
            this.blogDto.blogSummary = this.blogSummary;
            return this.fetchService.post(environment.apiUrl + '/blog', this.blogDto);
          },
        )).subscribe((res) => {

        if (res.message) {
          alert('Blog uploaded successfully.');

        } else {
          alert('There was an issue uploading the blog. Please contact admin!');
        }
        this.router.navigate([`blog`]);
      });
    } else {
      alert('Enter all values');
    }

  }

  public ngOnInit(): void {
    this.utilService.loader.next({state: 'off'});
  }

  public onChange(event: ChangeEvent): void {
    this.data = event.editor.getData();
    console.log(this.data);
    const plainText = (document.querySelector('ckeditor') as HTMLElement)?.innerText;
    this.minutesToRead = this.calculateMinutesToRead(plainText);
  }

  public calculateMinutesToRead(plainText: string): string {

    const minutesToRead = Math.floor(plainText.replace(/\n/g, ' ').split(' ').length / (this.averageAdultReadingSpeed));
    return Number(minutesToRead).toString();
  }

}
