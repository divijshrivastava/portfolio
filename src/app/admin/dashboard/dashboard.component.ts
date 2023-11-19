import {Component} from '@angular/core';
import {UtilService} from '../../services/util.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {

  constructor(private utilService: UtilService) {
  }

  ngOnInit() {
    this.utilService.loader.next({state: 'off'});
  }

}
