import {HttpHeaders} from '@angular/common/http';
import {Component} from '@angular/core';
import {environment} from '../../../environments/environment';
import {FetchServiceService} from '../../services/fetch-service.service';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.scss']
})
export class SettingsComponent {

  message: string = '';

  constructor(private fetchService: FetchServiceService) {
  }

  restartServer() {
    const headers = new HttpHeaders().set('Accept', 'text/plain');
    const responseType = 'text';

    this.fetchService.get(`${environment.serverUrl}/user-auth/restart`, {
      headers,
      responseType
    }).subscribe(
      (message) => {
        console.log(message);
        this.message = message;
      }, (error) => {
        console.error(error);
      },
    );
  }

}
