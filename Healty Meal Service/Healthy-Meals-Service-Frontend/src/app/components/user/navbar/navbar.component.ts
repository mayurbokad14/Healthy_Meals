import { Component } from '@angular/core';
import { AuthService } from '../user-service/auth.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
 
  constructor(private _authService: AuthService,
    private _router: Router, private toastr: ToastrService
  ){}

  logout(){
    this._authService.logout();
    this._authService.clearUserId();
    this._router.navigateByUrl('');
    this.toastr.success('Thank you! Visit Again','Logout Successful', {
      timeOut: 4000,
      progressBar: true,
      progressAnimation: 'decreasing',
      closeButton: true,
    });
  }
}
