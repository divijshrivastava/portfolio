import {Component, OnInit} from '@angular/core';
import {UserService} from '../services/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  user: string | undefined = 'There!';

  constructor(private userService: UserService) {
  }

  ngOnInit() {
    this.userService.isLoggedUser().subscribe((response: any) => this.user = response.message ? response.message : this.user);
    this.userService.isAdmin();
  }

}
