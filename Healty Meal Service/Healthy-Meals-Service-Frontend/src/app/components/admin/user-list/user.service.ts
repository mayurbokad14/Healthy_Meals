import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = 'http://localhost:8085/api/admin/users';

  constructor(private http: HttpClient) { }

  getUsers(pageNumber: number, pageSize: number, sortBy: string, sortDir: string): Observable<any> {
    const token = localStorage.getItem('admin-jwt');
    console.log(token);
    let headers:any;
    if (token) {
      headers = new HttpHeaders().set('Authorization', `Bearer ${token}`); // Add token in Authorization header
    }

    let params = new HttpParams()
      .set('pageNumber', pageNumber.toString())
      .set('pageSize', pageSize.toString())
      .set('sortBy', sortBy)
      .set('sortDir', sortDir);

    return this.http.get<any>(`${this.baseUrl}`, { params, headers });
  }
}
