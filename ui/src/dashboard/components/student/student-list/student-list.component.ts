import { Component } from '@angular/core';
import { DashboardService } from '../../../dashboard.service';
import { MatTableDataSource } from '@angular/material/table';
import { PageRequest } from '../../../../model/page-request.model';
import { Page, defaultPage } from '../../../../model/page.model';
import { Response } from '../../../../model/response.model';

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrl: './student-list.component.scss'
})
export class StudentListComponent {
  displayedColumns: string[] = ['name', 'roll', 'address', 'grade', 'action'];
  dataSource = new MatTableDataSource<any>([]);
  page: Page<any> = defaultPage;

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
    console.log(event);
    this.dashboardService.findInPage('student', params).subscribe((data: Response<Page<any>>) => {
      this.dataSource.data = data.data.content;
      this.page = data.data;
    });
  }

}
