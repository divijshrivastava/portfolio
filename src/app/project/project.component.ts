import {Component, OnInit} from '@angular/core';
import {UtilService} from '../services/util.service';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.scss']
})
export class ProjectComponent implements OnInit {

  constructor(private util: UtilService) {
  }

  ngOnInit(): void {
    this.util.heading.next({title: 'Project', url: '/project'});
  }

}
