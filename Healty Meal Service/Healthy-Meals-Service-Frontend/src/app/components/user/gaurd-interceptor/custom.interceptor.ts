import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse
} from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';
import { Router } from '@angular/router';

@Injectable()
export class CustomInterceptor implements HttpInterceptor {

  constructor(private router: Router) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const token = localStorage.getItem('user-jwt') || sessionStorage.getItem('user-jwt');
    if (token && this.isTokenExpired(token)) {
      // Token is expired
      localStorage.removeItem('user-jwt');
      this.router.navigateByUrl('user/login');  // Redirect to login page
      return throwError('Token expired');  // Stop further HTTP requests
    }

    if (token) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      });
    }

    return next.handle(request).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 401) {
          // If unauthorized (401), redirect to login
          localStorage.removeItem('user-jwt');
          this.router.navigateByUrl('user/login');
        }
        return throwError(error);
      })
    );
  }

  // Helper function to check if token is expired
  isTokenExpired(token: string): boolean {
    const tokenPayload = JSON.parse(atob(token.split('.')[1]));
    const expiry = tokenPayload.exp;
    return (Math.floor((new Date).getTime() / 1000)) >= expiry;
  }
}

