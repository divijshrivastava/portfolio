import {Component, OnInit} from '@angular/core';
import {environment} from '../../../environments/environment';
import {FetchServiceService} from '../../services/fetch-service.service';
import {UtilService} from '../../services/util.service';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.scss']
})
export class ProjectComponent implements OnInit {

  projectArray: any[] = [];

  constructor(private util: UtilService, private fetchService: FetchServiceService) {
  }

  ngOnInit(): void {
    this.util.heading.next({title: 'Project', url: '/project'});
    this.util.loader.next({state: 'off'});
    this.fetchService.get(`${environment.apiUrl}/project/1/1`).subscribe(resp => {
      this.projectArray = resp;
    });

  }

}
