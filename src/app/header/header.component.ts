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
  logoClass = 'logo';

  constructor(private route: Router, private util: UtilService) {
  }

  ngOnInit(): void {

    this.util.heading.subscribe(
      (obj) => {
        this.heading = obj;
      },
    );

    this.util.loader.subscribe((e) => {
      if (e.state === 'on') {
        this.logoClass = 'hide-logo';
      } else {
        this.logoClass = 'logo';
      }
    });
  }

  public openNav(): void {
    this.openNavEvent.emit(true);
  }

  goToHeadingPage() {
    this.route.navigateByUrl(this.heading.url);
  }


  @HostListener('window:scroll', ['$event'])
  onWindowScroll($event: any) {
    let scrollValue = $event.srcElement.scrollingElement.scrollTop;
    this.scrolled = scrollValue >= 31;
  }

}
