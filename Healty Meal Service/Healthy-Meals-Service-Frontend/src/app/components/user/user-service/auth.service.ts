import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private userId!: number;

  constructor(private http: HttpClient) {}
  private userUrl = 'http://localhost:8089/auth/user';


  registerUser(partnerData: any): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    return this.http.post<any>(this.userUrl+"/register"+ partnerData, { headers });
  }


  userLogin(username: string, password: string): Observable<any> {

    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    const body = { username, password };

    return this.http.post(this.userUrl +"/login",  body, {headers: headers }).pipe(
      map((response: any) => {
        // Store JWT in local storage
        if (response) {
          localStorage.setItem('user-jwt', response.token);  // Store the JWT token
        }
        return response;
      })
    );
  }

  // Optional: To logout the user and clear localStorage
  logout(): void {
    localStorage.removeItem('user-jwt');
  }

  // Optional: To get the JWT from localStorage
  getToken(): string | null {
    return localStorage.getItem('user-jwt');
  }


  setUserId(id: number): void {
    this.userId = id;
    console.log("userId: ", id)
    localStorage.setItem('userId', id.toString());  // Store userId in localStorage for persistence
  }

  getUserId(): number {
    return this.userId || parseInt(localStorage.getItem('userId') || '0', 10);
  }

   // Optionally, you can clear the userId when logged out
  clearUserId(): void {
    this.userId = 0;
    localStorage.removeItem('userId');
  }
}
