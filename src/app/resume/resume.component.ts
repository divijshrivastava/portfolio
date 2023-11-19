import {Component, OnInit} from '@angular/core';
import {UtilService} from '../services/util.service';

@Component({
  selector: 'app-resume',
  templateUrl: './resume.component.html',
  styleUrls: ['./resume.component.scss'],
})
export class ResumeComponent implements OnInit {

  resumeUrl: string = '';

  constructor(private util: UtilService) {
  }

  public ngOnInit() {
    this.resumeUrl = '/resume/view';
    this.util.heading.next({title: 'Resume', url: '/resume'});
    this.util.loader.next({state: 'off'});
  }

}
