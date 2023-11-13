import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-error',
  templateUrl: './error.component.html',
  styleUrls: ['./error.component.scss']
})
export class ErrorComponent implements OnInit {

  errorMessage: String = 'Sorry, The page was not found';

  constructor(private route: ActivatedRoute, private router: Router) {
  }

  ngOnInit(): void {
    console.log('Inside error component'); // , this.router.getCurrentNavigation()?.extras.state);

    this.route.queryParams.subscribe((queryParams) => {
      this.errorMessage = queryParams.message || 'Default error message';
    });
  }

}
