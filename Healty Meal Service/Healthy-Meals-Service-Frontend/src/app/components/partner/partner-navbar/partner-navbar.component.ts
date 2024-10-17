import { Component } from '@angular/core';
import { AuthServiceService } from '../partner-services/auth-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-partner-navbar',
  templateUrl: './partner-navbar.component.html',
  styleUrls: ['./partner-navbar.component.css']
})
export class PartnerNavbarComponent {
  constructor(private _authService: AuthServiceService, private _router: Router){}

  logout(){
    this._authService.logout();
    this._authService.clearPartnerId();
    this._router.navigateByUrl('');
  }

}
