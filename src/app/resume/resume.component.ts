import {HttpClient} from '@angular/common/http';
import {Component, OnInit} from '@angular/core';
import {UtilService} from '../services/util.service';

@Component({
  selector: 'app-resume',
  templateUrl: './resume.component.html',
  styleUrls: ['./resume.component.scss'],
})
export class ResumeComponent implements OnInit {

  resumeUrl: string = '';

  constructor(private http: HttpClient, private util: UtilService) {
    this.resumeUrl = '/resume/view';
  }

  public ngOnInit() {
    this.util.heading.next({title: 'Resume', url: '/resume'});
  }

}
