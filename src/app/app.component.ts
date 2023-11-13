import {Component, OnInit} from '@angular/core';
import {environment} from 'src/environments/environment';
import {UserService} from "./services/user.service";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  title = 'Divij';
  displayNav = false;

  constructor(private userService: UserService) {
  }

  ngOnInit(): void {
    console.log(`Environment is ${environment.envName}`);
    this.userService.fetchEntitlements();
  }

  showNav(event: boolean): void {
    this.displayNav = true;
  }

  closeNav(): void {
    this.displayNav = false;
  }
}
