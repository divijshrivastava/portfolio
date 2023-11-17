import {Component, EventEmitter, HostListener, OnInit, Output} from '@angular/core';
import {Router} from '@angular/router';
import {UtilService} from '../services/util.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  @Output()
  openNavEvent = new EventEmitter<boolean>();
  scrolled = false;
  heading: any = {};

  constructor(private route: Router, private util: UtilService) {
  }

  ngOnInit(): void {

    this.util.heading.subscribe(
      (obj) => {
        this.heading = obj;
      },
    );
    /*    this.route.events.subscribe(event => {
          if (event instanceof NavigationEnd) {
            const url = event.url;
            if (url === '/' || url === '') {
              this.heading = {title: '', url: ''};
            } else if (url.includes('/blogs')) {
              this.heading = {title: 'Blogs', url: '/blogs'};
            } else if (url.includes('/projects')) {
              this.heading = {title: 'Projects', url: '/projects'};
            } else if (url.includes('/admin')) {
              this.heading = {title: 'Admin Console', url: '/admin'};
            } else if (url.includes('/resume')) {
              this.heading = {title: 'Resume', url: '/resume'};
            }
          }
        });*/
  }

  public openNav(): void {
    this.openNavEvent.emit(true);
  }

  goToHeadingPage() {
    console.log('going to', this.heading.url);
    this.route.navigateByUrl(this.heading.url);
  }


  @HostListener('window:scroll', ['$event'])
  onWindowScroll($event: any) {
    let scrollValue = $event.srcElement.scrollingElement.scrollTop;
    this.scrolled = scrollValue >= 31;
  }

}
