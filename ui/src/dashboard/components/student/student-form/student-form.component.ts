import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { DashboardService } from '../../../dashboard.service';
import { StudentService } from '../student.service';

@Component({
  selector: 'app-student-form',
  templateUrl: './student-form.component.html',
  styleUrl: './student-form.component.scss'
})
export class StudentFormComponent {
  dataForm: FormGroup;
  courseList: any[] = [];
  deptList: any[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private studentService: StudentService,
    private dashboardService: DashboardService,
    private snackbar: MatSnackBar,
    private router: Router
  ) {
    this.dataForm = this.formBuilder.group({
      name: ['', Validators.required],
      roll: ['', Validators.required],
      address: [''],
      grade: [''],
      courses: [[''], Validators.required],
      dept: this.formBuilder.group({
        id: [[0], Validators.required]
      }),
      user: this.formBuilder.group({
        name: [''],
        username: ['', Validators.required],
        email: ['', [Validators.required, Validators.email]],
        password: ['', [Validators.required, Validators.minLength(2)]]
      })
    });
  }

  ngOnInit(): void {
    this.dashboardService.findAll('department').subscribe(data => {
      this.deptList = data.data;
    })
    this.dashboardService.findAll('course').subscribe(data => {
      this.courseList = data.data;
    })
  }

  onSubmit() {
    if (this.dataForm.valid) {
      const student = this.dataForm.value;
      student.courses = student.courses.map((element: any) => {
        return element.id ? element : { id: element };
      });
      student.dept.id = Number(student.dept.id);
      student.user.name = student.name;
      student.grade = student.grade.toString();
      this.dashboardService.save(student, 'student').subscribe(data => {
        this.snackbar.open(data.message, 'Close', {
          duration: 3000
        });
      });
    }
  }

}
