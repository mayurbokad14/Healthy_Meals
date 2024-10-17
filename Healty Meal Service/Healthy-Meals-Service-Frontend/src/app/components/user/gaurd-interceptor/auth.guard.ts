import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
 
  constructor(private router: Router) {}

  canActivate(): boolean {
    // const token = localStorage.getItem('loginToken');
    const token = localStorage.getItem('user-jwt') || sessionStorage.getItem('user-jwt');

    if (!token) {
      // If the token is not present, redirect to the login page
      this.router.navigate(['user/login']);
      return false;
    }
    return true;
  }
  
}
