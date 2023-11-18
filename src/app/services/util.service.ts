import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {BehaviorSubject, interval, Subscription} from 'rxjs';
import {map} from 'rxjs/operators';
import {environment} from '../../environments/environment';
import {FetchServiceService} from './fetch-service.service';

@Injectable({
  providedIn: 'root'
})
export class UtilService {

  public heading = new BehaviorSubject({title: '', url: ''});

  public obs: Subscription | undefined;
  private serverUp = false;
  private change = false;

  constructor(private fetchService: FetchServiceService, private router: Router) {
  }

  public ping() {
    this.obs = interval(10000).pipe(
      map((a) => this.fetchService.get(`${environment.serverUrl}/ping`)),
    ).subscribe((r) => {
      r.subscribe((v) => {
        console.log('Server is up!');
        if (this.change) {
          this.serverUp = true;
          this.change = !this.change;
          // @ts-ignore
          this.obs
          .unsubscribe();
          this.serverUp = !this.serverUp;
          this.router.navigate(['/']).then(() => {
            window.location.reload();
          });
        }
      }, (error) => {
        this.change = true;
        console.log('Server not up!');
      });
    });

    if (this.serverUp) {
      this.obs
      .unsubscribe();
      this.serverUp = !this.serverUp;
      this.router.navigate(['/']).then(() => {
        window.location.reload();
      });
    }

  }
}
