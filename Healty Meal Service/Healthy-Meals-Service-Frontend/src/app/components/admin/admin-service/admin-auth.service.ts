import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminAuthService {

  private adminLoginUrl = 'http://localhost:8085/auth/admin/login';  

  constructor(private http: HttpClient) { }

  adminLogin(adminName: string, password: string): Observable<any> {

    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    const body = { adminName, password };
    return this.http.post(this.adminLoginUrl, body, {headers: headers , responseType: 'text'
    }).pipe(
      map((response: any) => {

        console.log(response)
        if (response) {
          console.log(response);
          localStorage.setItem('admin-jwt', response);  
        }
        return response;
      })
    );
  }

  logout(): void {
    localStorage.removeItem('admin-jwt');
  }
}
