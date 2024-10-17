import { Component } from '@angular/core';
import { Partner } from '../model/Partner';
import { PartnerService } from '../partner-services/partner.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-edit-partner-profile',
  templateUrl: './edit-partner-profile.component.html',
  styleUrls: ['./edit-partner-profile.component.css']
})
export class EditPartnerProfileComponent {
  partner: Partner = {
    partnerId: 0,
    partnerName: '',
    shopName: '',
    partnerEmail: '',
    phoneNumber: '',
    password: '',
    createdAt: '',
    address: '',
    city: '',
    state: '',
    zipCode: '',
    shopDescription: '',
    logoUrl: ''
  };

  constructor(private partnerService: PartnerService, private router: Router) {}

  ngOnInit(): void {
    // Fetch the current partner details to populate the form
    this.partnerService.getPartnerById().subscribe(
      (data: Partner) => {
        this.partner = data;
      },
      (error) => {
        console.error('Error fetching partner data:', error);
      }
    );
  }

  onSubmit() {
    // Update the partner information
    this.partnerService.updatePartner(this.partner).subscribe(
      (updatedPartner: Partner) => {
        alert('Profile updated successfully');
        this.router.navigate(['partner/login']);  // Redirect to profile page after successful update
      },
      (error) => {
        console.error('Error updating profile:', error);
      }
    );
  }
}
