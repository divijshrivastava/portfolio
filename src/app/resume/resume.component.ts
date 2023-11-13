import {HttpClient} from '@angular/common/http';
import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-resume',
  templateUrl: './resume.component.html',
  styleUrls: ['./resume.component.scss'],
})
export class ResumeComponent implements OnInit {

  resumeUrl: string = '';

  constructor(private http: HttpClient) {
    this.resumeUrl = '/resume/view';
  }

  public ngOnInit() {
  }

}
