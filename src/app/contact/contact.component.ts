import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {environment} from '../../environments/environment';
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
  public contactForm: FormGroup;

  constructor(private fetchService: FetchServiceService, private fb: FormBuilder) {
    this.contactForm = this.fb.group({
      viewerName: [''],
      message: [''],
      email: [''],
    });
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
    console.log('saving user contact');
    this.userContactInfo = new UserContact(this.contactForm.value.viewerName, this.contactForm.value.email, this.contactForm.value.message);
    this.fetchService.post(`${environment.serverUrl}/user-contact`, this.userContactInfo).subscribe((resp) => {
      alert('form submitted');
    });
  }

}
