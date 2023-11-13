import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {environment} from 'src/environments/environment';
import {FetchServiceService} from './fetch-service.service';

@Injectable({
  providedIn: 'root',
})
export class UserService {

  public isAdminUser = false;

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

    if (environment.envName === 'dev') {
      return new Observable((subscriber) => {
        subscriber.next({
          message: 'TRUE',
          responseCode: 'SUCCESS',
          responseMessage: 'USER IS ADMIN',
        });
      });
    }

    return this.fetchService.post(`${environment.apiUrl}/user-auth/is-admin`).subscribe((resp: any) => {
      this.isAdminUser = (resp.message === 'TRUE');
    });

  }

  public fetchEntitlements() {

  }
}
