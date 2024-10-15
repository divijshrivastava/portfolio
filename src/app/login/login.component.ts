import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {UserService} from '../services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  errorMessage: string | null = null;
  showLoginForm = false;
  userName = '';

  constructor(private fb: FormBuilder, private userService: UserService, private router: Router) {
    this.loginForm = this.fb.group({
      userName: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  onSubmit() {
    this.userService.login(this.loginForm.value).subscribe({
      next: (response) => {
        // Handle successful login, e.g., store session info
        console.log('Login successful', response);
        this.router.navigateByUrl('/').then(() => {

          window.location.reload();
        });
      },
      error: (err) => {
        this.errorMessage = 'Invalid username or password.';
        console.error('Login error', err);
      },
    });
  }

  ngOnInit() {

    this.userService.fetchLoggedUserDetails().subscribe((response) => {
      if (response.message == null) {
        this.showLoginForm = true;
      } else {
        this.showLoginForm = false;
        this.userName = response.message;
      }
    }, (err) => {
      console.log('user not logged in!');
      this.showLoginForm = true;
    });
    console.log('Login form!');
  }

  logout() {
    this.userService.logout();
    this.router.navigateByUrl('/').then(() => {
      window.location.reload();
    });
  }

}
