import {Component, OnInit} from '@angular/core';
import {UserService} from '../services/user.service';
import {UtilService} from '../services/util.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  user: string | undefined = 'There!';

  constructor(private userService: UserService, private util: UtilService) {
  }

  ngOnInit() {
    this.userService.fetchLoggedUserDetails().subscribe((response: any) => this.user = response.message ? response.message : this.user);
    this.userService.isAdmin();
    this.util.heading.next({url: '#home', title: ''});
    this.util.loader.next({state: 'off'});
  }

}
