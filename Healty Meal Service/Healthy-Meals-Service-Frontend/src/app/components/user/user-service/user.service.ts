import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/User';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = 'http://localhost:8089/api/users'; 
  constructor(private http: HttpClient) { }


  getMeals(): Observable<any> {
    const token = localStorage.getItem('user-jwt');
    let headers:any;
    if (token) {
      headers = new HttpHeaders().set('Authorization', `Bearer ${token}`); // Add token in Authorization header
    }
    return this.http.get(`${this.baseUrl}/meals/all`, { headers });
  }

  // Get user by ID (fetched from local storage)
  getUserById(): Observable<User> {
    const userId = localStorage.getItem('userId'); // Assuming userId is stored in local storage

    const token = localStorage.getItem('user-jwt');
    let headers:any;
    if (token) {
      headers = new HttpHeaders().set('Authorization', `Bearer ${token}`); // Add token in Authorization header
    } 
    return this.http.get<User>(`${this.baseUrl}/${userId}`, { headers });
  }

  // Update user
  updateUser(user: User): Observable<User> {
    const userId = localStorage.getItem('userId');

    const token = localStorage.getItem('user-jwt');
    let headers:any;
    if (token) {
      headers = new HttpHeaders().set('Authorization', `Bearer ${token}`); // Add token in Authorization header
    } 
    return this.http.put<User>(`${this.baseUrl}/${userId}`, user, { headers });
  }

  // Delete user
  deleteUser(): Observable<void> {
    const userId = localStorage.getItem('userId'); // Assuming userId is stored in local storage

    const token = localStorage.getItem('user-jwt');
    let headers:any;
    if (token) {
      headers = new HttpHeaders().set('Authorization', `Bearer ${token}`); // Add token in Authorization header
    } 
    return this.http.delete<void>(`${this.baseUrl}/${userId}` , { headers });
  } 


  // Remove meal from cart
  removeFromCart(mealId: number, userId:number): Observable<any> {
    const token = localStorage.getItem('user-jwt');
    let headers:any;
    if (token) {
      headers = new HttpHeaders().set('Authorization', `Bearer ${token}`); // Add token in Authorization header
    }
    return this.http.delete(`${this.baseUrl}/${userId}/remove/${mealId}`, { headers });
  }
}
