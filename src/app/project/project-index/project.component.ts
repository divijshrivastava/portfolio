import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {environment} from '../../../environments/environment';
import {FetchServiceService} from '../../services/fetch-service.service';
import {UserService} from '../../services/user.service';
import {UtilService} from '../../services/util.service';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.scss']
})
export class ProjectComponent implements OnInit {

  projectArray: any[] = [];
  showCreateButton = false;

  constructor(private util: UtilService, private fetchService: FetchServiceService, private router: Router,
              private userService: UserService) {
  }

  ngOnInit(): void {
    this.util.heading.next({title: 'Project', url: '/project'});
    this.util.loader.next({state: 'off'});
    this.fetchService.get(`${environment.apiUrl}/project/1/1`).subscribe((resp) => {
      this.projectArray = resp;
    });
    this.authenticated();
  }

  navigateToCreateProject() {
    this.router.navigate(['/project/create']);
  }

  authenticated() {
    this.userService.entitlements.subscribe((resp: string[]) => {
      this.showCreateButton = resp.includes('ADMIN');
    });
  }

}
