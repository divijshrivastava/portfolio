import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {CookieService} from 'ngx-cookie-service'; // or any other method to get cookies
import {Observable} from 'rxjs';

@Injectable()
export class CsrfInterceptor implements HttpInterceptor {

  constructor(private cookieService: CookieService) {
  }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {

    const csrfToken = this.cookieService.get('XSRF-TOKEN'); // Get the CSRF token from cookies
    if (csrfToken) {
      // Clone the request and set the new header in one step.
      request = request.clone({
        setHeaders: {
          'X-XSRF-TOKEN': csrfToken // Add the CSRF token to the header
        }
      });
    }
    return next.handle(request);
  }
}
