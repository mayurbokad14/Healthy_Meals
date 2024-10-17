import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PartnerGuardGuard implements CanActivate {
  constructor(private router: Router) {}

  canActivate(): boolean {
    // const token = localStorage.getItem('loginToken');
    const token = localStorage.getItem('partner-jwt') || sessionStorage.getItem('partner-jwt');

    if (!token) {
      // If the token is not present, redirect to the login page
      this.router.navigate(['partner/login']);
      return false;
    }
    return true;
  }
  
}
