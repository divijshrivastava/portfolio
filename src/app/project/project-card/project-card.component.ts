import {Component, Input, SimpleChanges} from '@angular/core';
import {DomSanitizer, SafeResourceUrl} from '@angular/platform-browser';
import {Router} from '@angular/router';
import {environment} from 'src/environments/environment';
import {FetchServiceService} from '../../services/fetch-service.service';

@Component({
  selector: 'app-project-card',
  templateUrl: './project-card.component.html',
  styleUrls: ['./project-card.component.scss'],
})
export class ProjectCardComponent {

  @Input('parity') public parity = 'even';
  public isImagePresent = false;
  public imageSrc: string | undefined;
  public videoLink: SafeResourceUrl | undefined;
  public isVideoLinkPresent = false;
  public videoButtonLink: string | undefined;
  public projectType: string | undefined;
  public projectStatus: string | undefined;

  constructor(private sanitizer: DomSanitizer, private fetchService: FetchServiceService, private router: Router) {

  }

  private _data: any;

  get data(): any {
    return this._data;
  }

  @Input('data')
  set data(value: any) {
    this._data = value;
    this.videoLink =
      this.sanitizer.bypassSecurityTrustResourceUrl(value.videoLink);
  }

  ngOnChanges(changes: SimpleChanges) {
    this.videoLink = this.data.videoLink;
  }

  public ngOnInit() {

    this.projectType = this.data.projectType;
    this.projectStatus = this.data.status;
    if (this.projectType === 'WEBSITE') {
      this.isImagePresent = true;
      this.imageSrc = `${environment.apiUrl}/file/${this.data.imageId}`;
    } else if (this.projectType === 'YTVIDEO') {
      this.isImagePresent = false;
      this.videoButtonLink = this.data.videoLink;
      this.videoLink =
        this.sanitizer.bypassSecurityTrustResourceUrl(this.data.videoLink);
      console.log(this.videoLink);
      this.isVideoLinkPresent = true;
    } else if (this.projectType === 'CODE') {
      this.isImagePresent = this.data.isImagePresent;
      this.imageSrc = `${environment.apiUrl}/file/${this.data.imageId}`;
    }
  }

  public approveProject(projectId: any) {

    this.fetchService.post(`${environment.apiUrl}/project/approve/${projectId}`).subscribe(
      (resp) => {
        alert(resp.message);
        this.reloadCurrentRoute();
      },
    );
  }

  public deleteProject(projectId: any) {

    this.fetchService.delete(`${environment.apiUrl}/project/${projectId}`).subscribe(
      (resp) => {
        alert(resp.message);
        this.reloadCurrentRoute();
      },
    );
  }

  reloadCurrentRoute() {
    const currentUrl = this.router.url;
    this.router.navigateByUrl('/', {skipLocationChange: true}).then(() => {
      this.router.navigate([currentUrl]);
    });
  }
}
