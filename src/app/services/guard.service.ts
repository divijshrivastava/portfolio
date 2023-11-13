import {Injectable} from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  Router,
  RouterStateSnapshot,
  UrlTree
} from '@angular/router';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {environment} from 'src/environments/environment';
import {FetchServiceService} from './fetch-service.service';

@Injectable({
  providedIn: 'root'
})
export class CanActivateTeam implements CanActivate {

  constructor(private fetchService: FetchServiceService, private router: Router) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {

    return this.isLoggedUser().pipe(map((data: any) => {
      if (data.message != null) {
        return true;
      } else {
        this.router.navigateByUrl('blogs');
        return false;
      }
    }));

  }

  isLoggedUser() {

    if (environment.envName === 'dev') {
      return new Observable((subscriber: any) => {

        subscriber.next({message: 'divij', responseCode: 'User Found', responseMessage: null});
      });
    }

    return this.fetchService.post(`${environment.apiUrl}/user-auth/logged-user`);

  }

  /*async loggedUserName(){

    return await this.fetchService.post(`${environment.apiUrl}/user-auth/logged-user`).toPromise();
  }*/

}
