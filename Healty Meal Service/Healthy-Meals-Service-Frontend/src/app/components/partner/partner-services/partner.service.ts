import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Partner } from '../model/Partner';

@Injectable({
  providedIn: 'root',
})
export class PartnerService {
  private baseUrl = 'http://localhost:8084/api/partners'; // The URL to add meal
  private authUrl = 'http://localhost:8084/auth/partners/register';
  constructor(private http: HttpClient) {

  }


  getPartnerById(): Observable<Partner> {
    
    const token = localStorage.getItem('partner-jwt');
    let headers = new HttpHeaders().set('Content-Type', 'application/json');
    if (token) {
      headers = headers.set('Authorization', `Bearer ${token}`); // Add token in Authorization header
    }

    const partnerId = localStorage.getItem('partnerId'); // Assuming partnerId is stored in local storage
    return this.http.get<Partner>(`${this.baseUrl}/${partnerId}`,{headers});
  }

  // Update partner
  updatePartner(partner: Partner): Observable<Partner> {
    const token = localStorage.getItem('partner-jwt');
    let headers = new HttpHeaders().set('Content-Type', 'application/json');
    if (token) {
      headers = headers.set('Authorization', `Bearer ${token}`); // Add token in Authorization header
    }
    const partnerId = localStorage.getItem('partnerId');
    return this.http.put<Partner>(`${this.baseUrl}/${partnerId}`, partner,{headers});
  }

  // Delete partner
  deletePartner(): Observable<void> {
    const token = localStorage.getItem('partner-jwt');
    let headers = new HttpHeaders().set('Content-Type', 'application/json');
    if (token) {
      headers = headers.set('Authorization', `Bearer ${token}`); // Add token in Authorization header
    }
    const partnerId = localStorage.getItem('partnerId');
    return this.http.delete<void>(`${this.baseUrl}/${partnerId}`,{headers});
  }
  
  // Method to add meal to the backend
  addMeal(partnerId: number, mealData: any): Observable<any> {
    const token = localStorage.getItem('partner-jwt');
    console.log('JWT Token:', token);
    const url = `${this.baseUrl}/meals/add/${partnerId}`; // URL for the request
    let headers = new HttpHeaders().set('Content-Type', 'application/json');
    if (token) {
      headers = headers.set('Authorization', `Bearer ${token}`); // Add token in Authorization header
    }
    console.log(JSON.stringify(mealData));
    return this.http.post(url, mealData, { headers });
  }


  getMeals(partnerId: number): Observable<any> {
    const token = localStorage.getItem('partner-jwt');
    let headers:any;
    if (token) {
      headers = new HttpHeaders().set('Authorization', `Bearer ${token}`); // Add token in Authorization header
    }
    return this.http.get(`${this.baseUrl}/${partnerId}/meals`, { headers });
  }


  deleteMeal(mealId: number): Observable<any> {
    const token = localStorage.getItem('partner-jwt');
    let headers:any;
    if (token) {
      headers = new HttpHeaders().set('Authorization', `Bearer ${token}`); // Add token in Authorization header
    }
    return this.http.delete(`${this.baseUrl}/meals/delete/${mealId}`, { headers });
  }


  getMealById(mealId: number): Observable<any> {
    const token = localStorage.getItem('partner-jwt');
    let headers:any;
    if (token) {
      headers = new HttpHeaders().set('Authorization', `Bearer ${token}`); // Add token in Authorization header
    }
    return this.http.get(`${this.baseUrl}/meals/${mealId}`, { headers });
  }


  updateMeal(mealId: number, mealData: any): Observable<any> {
    const token = localStorage.getItem('partner-jwt');
    let headers:any;
    if (token) {
      headers = new HttpHeaders().set('Authorization', `Bearer ${token}`); // Add token in Authorization header
    }
    return this.http.put(`${this.baseUrl}/meals/update/${mealId}`, mealData, { headers });
  }

  registerPartner(partnerData: any): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    return this.http.post<any>(this.authUrl, partnerData, { headers });
  }
}
