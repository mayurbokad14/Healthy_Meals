import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private baseUrl = 'http://localhost:8086/api/cart';  // Replace with actual URL

  constructor(private http: HttpClient) { }

  // Fetch cart for the user
  getCart(userId: number): Observable<any> {
    const token = localStorage.getItem('user-jwt');
    let headers = new HttpHeaders();
    if (token) {
      headers = headers.set('Authorization', `Bearer ${token}`);
    }
    return this.http.get(`${this.baseUrl}/${userId}`, { headers });
  }

  // Add meal to cart
  addToCart(mealId: number, userId:number): Observable<any> {
    const token = localStorage.getItem('user-jwt');
    let headers:any;
    if (token) {
      headers = new HttpHeaders().set('Authorization', `Bearer ${token}`); // Add token in Authorization header
    }
    console.log(headers.json)
    return this.http.post(`${this.baseUrl}/${userId}/add/${mealId}`,{}, { headers });
  }

   // Remove meal from cart
   removeFromCart(mealId: number, userId: number, removeAll: boolean = false): Observable<any> {
    const token = localStorage.getItem('user-jwt');
    let headers = new HttpHeaders();
    if (token) {
      headers = headers.set('Authorization', `Bearer ${token}`);
    }
    const url = removeAll ? `${this.baseUrl}/${userId}/remove/${mealId}` : `${this.baseUrl}/${userId}/remove/${mealId}`;
    return this.http.delete(url, { headers });
  }
}
