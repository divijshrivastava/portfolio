import {HttpClient} from '@angular/common/http';
import {Component, OnInit} from '@angular/core';
import {PageEvent} from '@angular/material/paginator';
import {environment} from '../../../environments/environment';
import {UserActivityService} from '../../services/user-activity.service';
import {UtilService} from '../../services/util.service';
import {UserActivity} from './UserActivity';

@Component({
  selector: 'app-user-activity',
  templateUrl: './user-activity.component.html',
  styleUrls: ['./user-activity.component.scss'],
})
export class UserActivityComponent implements OnInit {

  userActivities: UserActivity[] = [];
  currentPage = 2;
  itemsPerPage = 10;
  totalActivities: number = 0;
  pageSize = 10;
  pageIndex = 1;

  constructor(private http: HttpClient, private userActivityService: UserActivityService, private utilService: UtilService) {
  }

  ngOnInit(): void {
    this.loadUserActivity(this.currentPage - 1, this.itemsPerPage);
    this.utilService.loader.next({state: 'off'});
  }

  loadUserActivity(page: number, size: number): void {
    const url = `${environment.apiUrl}/user-activity/all?page=${page}&size=${size}`;
    this.http.get<UserActivity[]>(url).subscribe((data: any) => this.userActivities = data.content);
    this.http.get<number>('/user-activity/total-activities').subscribe((total) => {
      this.totalActivities = total;
    });
  }

  nextPage(): void {
    this.currentPage++;
    this.loadUserActivity(this.currentPage - 1, this.itemsPerPage);
  }

  prevPage(): void {
    if (this.currentPage > 1) {
      this.currentPage--;
      this.loadUserActivity(this.currentPage - 1, this.itemsPerPage);
    }
  }

  getPages(): number[] {
    const pageCount = Math.ceil(this.userActivities.length / this.itemsPerPage);
    return Array.from({length: pageCount}, (_, i) => i + 1);
  }

  onPageChange(event: PageEvent) {
    this.pageIndex = event.pageIndex;
    this.pageSize = event.pageSize;
    this.userActivityService.getUserActivities(this.pageIndex, this.pageSize).subscribe(response => {
      this.userActivities = response.content;
      //this.totalActivities = response.totalElements;
    });
  }

}
