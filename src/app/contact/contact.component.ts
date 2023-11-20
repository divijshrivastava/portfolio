import {Component, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {RecaptchaComponent} from 'ng-recaptcha';

import {environment} from '../../environments/environment';
import {FetchServiceService} from '../services/fetch-service.service';
import {UserContact} from './UserContact';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.scss'],
})
export class ContactComponent implements OnInit {

  public userContactInfo!: UserContact;
  public contactForm: FormGroup;
  public resolvedCaptcha = false;

  @ViewChild('recaptcha')
  recaptchaComponent!: RecaptchaComponent;

  constructor(private fetchService: FetchServiceService,
              private fb: FormBuilder) {
    this.contactForm = this.fb.group({
      viewerName: [''],
      message: [''],
      email: [''],
    });
  }

  public ngOnInit(): void {
  }

  public resolved(captchaResponse: string): void {
    // This will be called when the user submits the reCAPTCHA response
    this.resolvedCaptcha = true;
    console.log(`Submitted captcha! ${captchaResponse}`);
  }

  public saveUserContact() {
    console.log('saving user contact');
    this.userContactInfo = new UserContact(this.contactForm.value.viewerName,
      this.contactForm.value.email,
      this.contactForm.value.message);
    this.fetchService
    .post(`${environment.serverUrl}/user-contact`, this.userContactInfo)
    .subscribe((resp) => {
      this.recaptchaComponent.reset();
      this.resolvedCaptcha = !this.resolvedCaptcha;
      this.contactForm.reset();
      console.log(this.recaptchaComponent.id, this.recaptchaComponent.badge);
      alert('Hey!, I got your message. I will contact you soon. Cheers! - Divij');
    });
  }

  public recaptchaError($event: any) {
    console.log(`An error occured: ${$event}`);
    this.resolvedCaptcha = false;
  }
}
