import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { apiUrl } from '../constants';
import { Observable } from 'rxjs';
import { Response } from '../model/Response';

@Injectable()
export class DashboardService {

  constructor(private http: HttpClient) { }

  save(data: any, model: string): Observable<Response<any>> {
    return this.http.post<Response<any>>(`${apiUrl}/${model}/save`, data, {
      headers: {
        'Content-Type': 'application/json'
      }
    });
  }

  delete(id: number, model: string): Observable<Response<any>> {
    return this.http.delete<Response<any>>(`${apiUrl}/${model}/delete/${id}`);
  }

  findById(id: number, model: string): Observable<Response<any>> {
    return this.http.get<Response<any>>(`${apiUrl}/${model}/find/${id}`);
  }

  findAll(model: string): Observable<Response<any>> {
    return this.http.get<Response<any>>(`${apiUrl}/${model}`);
  }

}
