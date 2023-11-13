import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';
import {FetchServiceService} from './fetch-service.service';

@Injectable({
  providedIn: 'root',
})
export class UserActivityService {

  constructor(private fetchService: FetchServiceService) {
  }

  public getUserActivities(pageIndex: number, pageSize: number): Observable<any> {
    return this.fetchService.get(
      `${environment.serverUrl}/user-activity/all?page=${pageIndex}&size=${pageSize}`
    );
  }
}
