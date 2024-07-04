import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HomeService } from '../home.service';
import { Response, ResponseStatus } from '../../model/response.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  loginForm: FormGroup;
  constructor(
    private formBuilder: FormBuilder,
    private homeService: HomeService,
    private router: Router
  ) {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {
      this.homeService.login(this.loginForm.value).subscribe((resp: Response<any>) => {
        console.log(resp);
        console.log(resp.status == ResponseStatus.SUCCESS);
        if (resp.status == ResponseStatus.SUCCESS) {
          localStorage.setItem('token', resp.data);
          this.router.navigate(['/dashboard']);
        }
      });
    }
  }
}
