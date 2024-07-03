import { Component } from '@angular/core';
import { DashboardService } from '../../../dashboard.service';
import { MatTableDataSource } from '@angular/material/table';
import { PageRequest } from '../../../../model/PageRequest';

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrl: './student-list.component.scss'
})
export class StudentListComponent {
  studentList: any[] = [];
  displayedColumns: string[] = ['name', 'roll', 'address', 'grade', 'action'];
  dataSource = new MatTableDataSource<any>(this.studentList);

  constructor(private dashboardService: DashboardService) { }

  ngOnInit() {
    const params = new PageRequest(1, 10);
    this.dashboardService.findInPage('student', params).subscribe(data => {
      this.studentList = data.data;
    });
  }

  onDelete(studentId: number) {
    console.log('delete', studentId);
  }
  onEdit(studentId: number) {
    console.log('edit', studentId);
  }

}
