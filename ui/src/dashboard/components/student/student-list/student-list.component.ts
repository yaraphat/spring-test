import { Component, ViewChild } from '@angular/core';
import { MatSort, Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { PageRequest } from '../../../../model/page-request.model';
import { Page, defaultPage } from '../../../../model/page.model';
import { Response } from '../../../../model/response.model';
import { DashboardService } from '../../../dashboard.service';

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrl: './student-list.component.scss'
})
export class StudentListComponent {

  @ViewChild(MatSort) sort!: MatSort;

  displayedColumns: string[] = ['name', 'roll', 'address', 'grade', 'action'];
  dataSource = new MatTableDataSource<any>([]);
  page: Page<any> = defaultPage;
  filterText?: string;
  timer: any = null;

  constructor(private dashboardService: DashboardService) { }

  fetchPage(pageNumber: number, pageSize: number) {
    const params = new PageRequest(pageNumber, pageSize);
    params.sortColumn = this.sort?.active;
    params.order = this.sort?.direction;
    params.search = this.filterText;
    this.dashboardService.findInPage('student', params).subscribe((data: Response<Page<any>>) => {
      this.dataSource.data = data.data.content;
      this.page = data.data;
    });
  }

  ngOnInit() {
    this.fetchPage(0, 10);
  }

  onDelete(studentId: number) {
    console.log('delete', studentId);
  }
  onEdit(studentId: number) {
    console.log('edit', studentId);
  }

  pageChanged(event: any) {
    this.fetchPage(event.pageIndex, event.pageSize);
  }

  onSortChange(sortState: Sort) {
    this.fetchPage(0, this.page.size);
  }

  onSearch() {
    if (this.timer) {
      clearTimeout(this.timer);
    }
    this.timer = setTimeout(() => {
      this.fetchPage(0, this.page.size);
    }, 500);
  }

}
