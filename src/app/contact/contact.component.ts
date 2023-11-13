import {Component, OnInit} from '@angular/core';
import {FetchServiceService} from '../services/fetch-service.service';
import {UserContact} from './UserContact';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  // tslint:disable-next-line:object-literal-sort-keys
  styleUrls: ['./contact.component.scss'],
})
export class ContactComponent implements OnInit {

  public userContactInfo!: UserContact;

  constructor(private fetchService: FetchServiceService) {
  }

  public ngOnInit(): void {
  }

  public alert(message: string): void {
    alert(message);
  }

  public resolved(captchaResponse: string): void {
    // This will be called when the user submits the reCAPTCHA response
    alert('Submitted captcha!');
  }

  public saveUserContact() {
    this.fetchService.post('user-contact', this.userContactInfo).subscribe((resp) => {

    });
  }

}
