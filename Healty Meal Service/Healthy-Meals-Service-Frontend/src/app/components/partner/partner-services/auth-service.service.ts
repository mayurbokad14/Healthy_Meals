import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  private partnerUrl = 'http://localhost:8084/auth/partners/login';
  
  private partnerId!: number;

  constructor(private http: HttpClient) {}

  partnerLogin(partnerName: string, password: string): Observable<any> {

    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    const body = { partnerName, password };
    
   
    console.log(partnerName);
    console.log(password);

    return this.http.post(this.partnerUrl, body, {headers: headers
    }).pipe(
      map((response: any) => {
        // Store JWT in local storage
        console.log(response.json)
        if (response) {
          console.log(response.token);
          localStorage.setItem('partner-jwt', response.token);  // Store the JWT token
        }
        return response;
      })
    );
  }

 

  // Optional: To logout the user and clear localStorage
  logout(): void {
    localStorage.removeItem('partner-jwt');
  }

  // Optional: To get the JWT from localStorage
  getToken(): string | null {
    return localStorage.getItem('partner-jwt');
  }


  setPartnerId(id: number): void {
    this.partnerId = id;
    localStorage.setItem('partnerId', id.toString());  // Store partnerId in localStorage for persistence
  }

  getPartnerId(): number {
    return this.partnerId || parseInt(localStorage.getItem('partnerId') || '0', 10);
  }

   // Optionally, you can clear the partnerId when logged out
  clearPartnerId(): void {
    this.partnerId = 0;
    localStorage.removeItem('partnerId');
  }
}
