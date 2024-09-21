import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FetchServiceService {

  constructor(private http: HttpClient) {
  }

  get(url: string, options?: any): Observable<any> {

    return this.http.get(url,
      options);

  }

  post(url: string, item?: any): Observable<any> {
    return this.http.post(url, item);
  }

  delete(url: string, item?: any): Observable<any> {
    return this.http.delete(url, item);
  }

}
