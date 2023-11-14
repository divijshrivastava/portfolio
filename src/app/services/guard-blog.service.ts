import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {FetchServiceService} from './fetch-service.service';
import {UserService} from './user.service';

@Injectable({
  providedIn: 'root'
})
export class BlogGuard implements CanActivate {

  constructor(private fetchService: FetchServiceService, private router: Router, private userService: UserService) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot):
    boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {

    return this.userService.entitlements.pipe(map((data: string[]) => {
      if (data.includes('AUTHOR')) {
        return true;
      } else {
        this.router.navigate(['/blogs']);
        return false;
      }
    }));

  }

}
