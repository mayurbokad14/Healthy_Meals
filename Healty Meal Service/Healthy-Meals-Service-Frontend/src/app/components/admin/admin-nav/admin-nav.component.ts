import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AdminAuthService } from '../admin-service/admin-auth.service';

@Component({
  selector: 'app-admin-nav',
  templateUrl: './admin-nav.component.html',
  styleUrls: ['./admin-nav.component.css']
})
export class AdminNavComponent {  
  
  constructor(private _authService: AdminAuthService, private _router: Router){}

  logout(){
    this._authService.logout();
    this._router.navigateByUrl('');
  }

}
