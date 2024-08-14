import {Inject, Injectable} from '@angular/core';
import {Title} from '@angular/platform-browser';
import {NavigationEnd, Router} from '@angular/router';
import {BehaviorSubject, interval, Subscription} from 'rxjs';
import {map} from 'rxjs/operators';
import {environment} from '../../environments/environment';
import {WINDOW} from '../shared/window.token';
import {FetchServiceService} from './fetch-service.service';

@Injectable({
  providedIn: 'root'
})
export class UtilService {

  public heading = new BehaviorSubject({title: '', url: ''});

  public obs: Subscription | undefined;
  private serverUp = false;
  private change = false;

  public loader = new BehaviorSubject({state: 'on'});

  constructor(private fetchService: FetchServiceService, private router: Router, private title: Title,
              @Inject(WINDOW) private window: Window) {
  }

  public updateTitle(title: string) {
    this.title.setTitle(title);
  }

  public ping() {
    this.obs = interval(10000).pipe(
      map((a) => this.fetchService.get(`${environment.serverUrl}/ping`)),
    ).subscribe((r) => {
      r.subscribe((v) => {
        if (this.change) {
          this.serverUp = true;
          this.change = !this.change;
          // @ts-ignore
          this.obs
          .unsubscribe();
          this.serverUp = !this.serverUp;
          this.router.navigate(['/']).then(() => {
            this.window.location.reload();
          });
        }
      }, (error) => {
        this.change = true;
      });
    });

    if (this.serverUp) {
      this.obs
      .unsubscribe();
      this.serverUp = !this.serverUp;
      this.router.navigate(['/']).then(() => {
        this.window.location.reload();
      });
    }

  }

  public scrollToTop() {
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.window.scrollTo({top: 0, behavior: 'smooth'});
      }
    });
  }
}
