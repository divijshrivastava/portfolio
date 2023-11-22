import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, NavigationSkipped, NavigationStart, Router} from '@angular/router';
import {filter} from 'rxjs/operators';
import {UserService} from './services/user.service';
import {UtilService} from './services/util.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  title = 'Divij';
  displayNav = false;
  imageStyle: any = {
    'width': '40px',
    'height': 'auto',
    'position': 'absolute',
    'top': '50%',
    'left': '50%',
    '-webkit-transform': 'translate(-50%, -50%)',
    'transform': 'translate(-50%, -50%)',
    'z-index': '55',
    'animation': 'rotation 0.5s infinite linear',
  };

  constructor(private userService: UserService,
              private utilService: UtilService,
              private router: Router, private activatedRoute: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.userService.fetchEntitlements();
    this.utilService.loader.subscribe((a) => {
      if (a.state === 'on') {
        this.imageStyle = {
          'width': '40px',
          'height': 'auto',
          'position': 'absolute',
          'top': '50%',
          'left': '50%',
          '-webkit-transform': 'translate(-50%, -50%)',
          'transform': 'translate(-50%, -50%)',
          'z-index': '55',
          'animation': 'rotation 0.5s infinite linear',
          'display': 'block'
        };
      } else {
        this.imageStyle = {
          'width': '40px',
          'height': 'auto',
          'position': 'absolute',
          'top': '50%',
          'left': '50%',
          '-webkit-transform': 'translate(-50%, -50%)',
          'transform': 'translate(-50%, -50%)',
          'z-index': '55',
          'animation': 'rotation 0.5s infinite linear',
          'display': 'none'
        };
      }
    });

    this.router.events.pipe(filter(e => e instanceof NavigationSkipped)).subscribe(
      (e) => {
        this.utilService.loader.next({state: 'off'});
      },
    );
    this.router.events.pipe(filter((e) => e instanceof NavigationStart)).subscribe((e) => {
      this.utilService.loader.next({state: 'on'});
    });
    this.activatedRoute.fragment.subscribe(e => {
      this.utilService.loader.next({state: 'off'});
    });
    this.utilService.scrollToTop();
  }

  showNav(event: boolean): void {
    this.displayNav = true;
  }

  closeNav(): void {
    this.displayNav = false;
  }
}
