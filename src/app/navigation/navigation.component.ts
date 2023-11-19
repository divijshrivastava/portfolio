import {AfterViewInit, Component, EventEmitter, OnDestroy, OnInit, Output} from '@angular/core';
import {Router} from '@angular/router';
import {UserService} from '../services/user.service';
import {UtilService} from '../services/util.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent implements OnInit, AfterViewInit, OnDestroy {

  isAdmin = false;

  @Output()
  closeNavEvent: EventEmitter<boolean> = new EventEmitter<boolean>();

  constructor(private router: Router, private userService: UserService, private utilService: UtilService) {
  }

  closeNav(): void {
    this.closeNavEvent.emit(true);
  }

  getTop(): any {
    const scrollTop = (window.pageYOffset !== undefined) ?
      window.pageYOffset : (document.documentElement || document.body.parentNode || document.body).scrollTop;
    return scrollTop;
  }

  getLeft(): any {
    const scrollLeft = (window.pageXOffset !== undefined) ?
      window.pageXOffset : (document.documentElement || document.body.parentNode || document.body).scrollLeft;
    return scrollLeft;
  }

  ngAfterViewInit(): void {
    const scrollLeft = (window.pageXOffset !== undefined) ?
      window.pageXOffset : (document.documentElement || document.body.parentNode || document.body).scrollLeft;
    const scrollTop = (window.pageYOffset !== undefined) ?
      window.pageYOffset : (document.documentElement || document.body.parentNode || document.body).scrollTop;
    (document.querySelector('.nav-wrapper') as HTMLElement).style.top = scrollTop + 'px';
    (document.querySelector('.nav-wrapper') as HTMLElement).style.left = scrollLeft + 'px';

    const html = document.querySelector('html') as HTMLElement; // html.classList.add('scroll-stop');
    const body = document.querySelector('body') as HTMLElement;
    body.classList.add('scroll-stop');
  }

  ngOnInit(): void {
    this.userService.isAdmin().subscribe((data) => {
      this.isAdmin = data;
    });

  }

  ngOnDestroy(): void {
    const html = document.querySelector('html') as HTMLElement;
    html.classList.remove('scroll-stop');
    const body = document.querySelector('body') as HTMLElement;
    body.classList.remove('scroll-stop');
  }

  navigateTo(link: string): void {
    this.closeNav();
    this.router.navigate([link]);
  }

}
