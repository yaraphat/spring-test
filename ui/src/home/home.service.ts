import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { apiUrl } from '../constants';
import { Observable } from 'rxjs';
import { Response } from '../model/response.model';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  constructor(private http: HttpClient) { }

  login(userData: { username: string, password: string }): Observable<Response<any>> {
    return this.http.post<Response<any>>(`${apiUrl}/user/login`, userData);
  }

  signup(userData: any): Observable<Response<any>> {
    return this.http.post<Response<any>>(`${apiUrl}/user/save`, userData);
  }
}
