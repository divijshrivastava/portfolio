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

  public fetchLoggedUserDetails(): Observable<any> | Observable<unknown> {

    return this.fetchService.post(`${environment.apiUrl}/user-auth/logged-user`);
  }

  public isAdmin() {

    return this.entitlements.pipe(map((data: string[]) => {
      return data.includes('ADMIN');
    }));

  }

  public fetchEntitlements(): void {
    this.fetchService.get(`${environment.apiUrl}/user-auth/entitlements`).subscribe((resp: any) => {
      this.entitlements.next(resp);
      this.entitlementsFetched = true;
    });
  }

  public login(credentials: { username: string; password: string }): Observable<any> {
    return this.fetchService.post(`${environment.apiUrl}/login`, credentials);
  }

  public logout(): void {
    this.fetchService.post(`${environment.apiUrl}/logout`).subscribe(() => {
      console.log('User logged out!');
    });
  }
}
