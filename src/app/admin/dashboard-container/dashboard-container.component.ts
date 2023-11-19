import {Component} from '@angular/core';
import {UtilService} from '../../services/util.service';

@Component({
  selector: 'app-dashboard-container',
  templateUrl: './dashboard-container.component.html',
  styleUrls: ['./dashboard-container.component.scss']
})
export class DashboardContainerComponent {

  constructor(private util: UtilService) {

  }

  ngOnInit() {
    this.util.heading.next({title: 'Admin', url: '/admin'});
    this.util.loader.next({state: 'off'});
  }

}
