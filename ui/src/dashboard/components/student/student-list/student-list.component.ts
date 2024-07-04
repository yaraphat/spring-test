import { Component, ViewChild } from '@angular/core';
import { MatSort, Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { PageRequest } from '../../../../model/page-request.model';
import { Page, defaultPage } from '../../../../model/page.model';
import { Response } from '../../../../model/response.model';
import { DashboardService } from '../../../dashboard.service';
import { debounce } from '../../../util/concurrent.util';

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrl: './student-list.component.scss'
})
export class StudentListComponent {
  displayedColumns: string[] = ['name', 'roll', 'address', 'grade', 'action'];
  dataSource = new MatTableDataSource<any>([]);
  page: Page<any> = defaultPage;
  filterText?: string;
  timer: any = null;

  constructor(private dashboardService: DashboardService) { }

  ngOnInit() {
    const params = new PageRequest(1, 10);
    this.dashboardService.findInPage('student', params).subscribe((data: Response<Page<any>>) => {
      this.dataSource.data = data.data.content;
      this.page = data.data;
    });
  }

  onDelete(studentId: number) {
    console.log('delete', studentId);
  }
  onEdit(studentId: number) {
    console.log('edit', studentId);
  }

  pageChanged(event: any) {
    const params = new PageRequest(event.pageIndex, event.pageSize);
    params.search = this.filterText || '*';
    params.sortColumn = this.sort.active;
    params.order = this.sort.direction || 'asc';
    this.dashboardService.findInPage('student', params).subscribe((data: Response<Page<any>>) => {
      this.dataSource.data = data.data.content;
      this.page = data.data;
    });
  }

  @ViewChild(MatSort) sort!: MatSort;

  onSortChange(sortState: Sort) {
    const params = new PageRequest(1, this.page.size);
    params.sortColumn = sortState.active;
    params.order = sortState.direction || 'asc';
    params.search = this.filterText || '*';
    this.dashboardService.findInPage('student', params).subscribe((data: Response<Page<any>>) => {
      this.dataSource.data = data.data.content;
      this.page = data.data;
    });
  }

  onSearch(event: any) {
    if (this.timer) {
      clearTimeout(this.timer);
    }
    this.timer = setTimeout(() => {
      const params = new PageRequest(1, this.page.size);
      params.sortColumn = this.sort.active;
      params.order = this.sort.direction || 'asc';
      params.search = this.filterText || '*';
      this.dashboardService.findInPage('student', params).subscribe((data: Response<Page<any>>) => {
        this.dataSource.data = data.data.content;
        this.page = data.data;
      });
    }, 500);

  }

}
