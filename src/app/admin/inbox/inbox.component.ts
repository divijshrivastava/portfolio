import {Component} from '@angular/core';
import {PageEvent} from '@angular/material/paginator';
import {Observable} from 'rxjs';

import {environment} from '../../../environments/environment';
import {UserContact} from '../../contact/UserContact';
import {FetchServiceService} from '../../services/fetch-service.service';
import {UtilService} from '../../services/util.service';

@Component({
  selector: 'app-inbox',
  templateUrl: './inbox.component.html',
  styleUrls: ['./inbox.component.scss']
})
export class InboxComponent {
  userContact: UserContact[] = [];
  currentPage = 2;
  itemsPerPage = 10;
  totalContact: number = 0;
  pageSize = 10;
  pageIndex = 1;

  constructor(private fetchService: FetchServiceService,
              private utilService: UtilService) {
  }

  onPageChange(event: PageEvent) {
    this.pageIndex = event.pageIndex;
    this.pageSize = event.pageSize;
    this.fetchUserContact(this.pageIndex, this.pageSize).subscribe(response => {
      this.userContact = response.content;
      // this.totalActivities = response.totalElements;
    });
  }

  public fetchUserContact(page: number, size: number): Observable<any> {
    return this.fetchService.get(
      `${environment.apiUrl}/user-contact/all?page=${page}&size=${size}`);
  }

  public fetchTotalContacts() {
    return this.fetchService
    .get(`${environment.apiUrl}/user-contact/total-contacts`)
    .subscribe(
      (resp) => {
        this.totalContact = resp;
      },
    );
  }

  ngOnInit() {
    this.fetchTotalContacts();
    this.fetchUserContact(this.pageIndex, this.pageSize);
    this.utilService.loader.next({state: 'off'});
    this.fetchUserContact(this.pageIndex, this.pageSize).subscribe(response => {
      this.userContact = response.content;
      // this.totalActivities = response.totalElements;
    });
  }
}
