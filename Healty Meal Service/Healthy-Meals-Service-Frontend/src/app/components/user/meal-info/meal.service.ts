import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MealService {

  private baseUrl = 'http://localhost:8082/api/meals';  // Replace with your actual backend URL

  constructor(private http: HttpClient) { }

  // Get meal and partner information
  getMealPartner(mealId: number): Observable<any> {
    const token = localStorage.getItem('user-jwt');
    let headers = new HttpHeaders();
    if (token) {
      headers = headers.set('Authorization', `Bearer ${token}`);
    }
    return this.http.get(`${this.baseUrl}/meal-partner/${mealId}`, { headers });
  }
}
