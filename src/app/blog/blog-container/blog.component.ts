import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';


@Component({
  selector: 'app-blog',
  templateUrl: './blog.component.html',
  styleUrls: ['./blog.component.scss']
})
export class BlogContainerComponent implements OnInit {

  blogs = new Array(5);

  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }

  goToBlog() {
    this.router.navigateByUrl("blogs/1");
    console.log("Navigating");
  }

}
