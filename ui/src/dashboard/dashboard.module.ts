import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDividerModule } from '@angular/material/divider';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatMenuModule } from '@angular/material/menu';
import { MatSelectModule } from '@angular/material/select';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatTreeModule } from '@angular/material/tree';
import { RouterModule } from '@angular/router';
import { StudentFormComponent } from './components/student/student-form/student-form.component';
import { StudentListComponent } from './components/student/student-list/student-list.component';
import { dashboardRoutes } from './dashboard.routes';
import { HeaderComponent } from './layout/header/header.component';
import { MainComponent } from './layout/main/main.component';
import { SidenavComponent } from './layout/sidenav/sidenav.component';
import { CourseComponent } from './components/course/course.component';
import { DepartmentComponent } from './components/department/department.component';
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { DashboardService } from './dashboard.service';
import { StudentService } from './components/student/student.service';
import { CourseService } from './components/course/course.service';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';

@NgModule({
  declarations: [
    HeaderComponent,
    SidenavComponent,
    MainComponent,
    StudentFormComponent,
    StudentListComponent,
    CourseComponent,
    DepartmentComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    MatTreeModule,
    MatSidenavModule,
    MatToolbarModule,
    MatMenuModule,
    MatIconModule,
    MatDividerModule,
    MatListModule,
    RouterModule.forChild(dashboardRoutes),
    ReactiveFormsModule,
    MatSelectModule,
    MatFormFieldModule,
    MatButtonModule,
    MatTableModule,
    MatPaginatorModule
  ],
  providers: [provideHttpClient(withInterceptorsFromDi()), DashboardService, StudentService, CourseService]
})
export class DashboardModule { }
