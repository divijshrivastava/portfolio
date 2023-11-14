import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {environment} from 'src/environments/environment';
import {FetchServiceService} from './fetch-service.service';

@Injectable({
  providedIn: 'root',
})
export class UserService {

  public isAdminUser = false;
  public entitlements = new BehaviorSubject([]);
  public entitlementsFetched = false;

  constructor(private fetchService: FetchServiceService) {
  }

  public isLoggedUser(): Observable<any> | Observable<unknown> {

    if (environment.envName === 'dev') {
      return new Observable((subscriber) => {
        subscriber.next({
          message: 'John Doe',
          responseCode: 'User Found',
          responseMessage: null,
        });
      });
    }
    return this.fetchService.post(`${environment.apiUrl}/user-auth/logged-user`);
  }

  public isAdmin() {

    /*if (environment.envName === 'dev') {
      return new Observable((subscriber) => {
        subscriber.next({
          message: 'TRUE',
          responseCode: 'SUCCESS',
          responseMessage: 'USER IS ADMIN',
        });
      });
    }*/

    return this.entitlements.pipe(map((data) => {
      return data.includes('ADMIN');
    }));

  }

  public fetchEntitlements() {
    this.fetchService.get(`${environment.apiUrl}/user-auth/entitlements`).subscribe((resp: any) => {
      this.entitlements.next(resp);
      this.entitlementsFetched = true;
    });
  }
}
