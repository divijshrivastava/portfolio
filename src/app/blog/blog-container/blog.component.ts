import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {UtilService} from '../../services/util.service';

@Component({
  selector: 'app-blog',
  templateUrl: './blog.component.html',
  styleUrls: ['./blog.component.scss']
})
export class BlogContainerComponent implements OnInit {

  blogs = new Array(5);

  constructor(private router: Router, private util: UtilService) {
  }

  ngOnInit(): void {
    this.util.heading.next({title: 'Blog', url: '/blog'});
    this.util.loader.next({state: 'on'});
  }

}
