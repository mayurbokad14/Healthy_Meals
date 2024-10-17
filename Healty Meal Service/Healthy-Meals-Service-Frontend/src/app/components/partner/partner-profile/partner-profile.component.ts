import { Component } from '@angular/core';
import { Partner } from '../model/Partner';
import { PartnerService } from '../partner-services/partner.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-partner-profile',
  templateUrl: './partner-profile.component.html',
  styleUrls: ['./partner-profile.component.css']
})
export class PartnerProfileComponent {
  partner: Partner | undefined;

  constructor(private partnerService: PartnerService, private router: Router) {}

  ngOnInit(): void {
    // Fetch partner details by ID (from local storage)
    this.partnerService.getPartnerById().subscribe(
      (data: Partner) => {
        this.partner = data;
      },
      (error) => {
        console.error('Error fetching partner data:', error);
      }
    );
  }

  onEditProfile() {
    this.router.navigate(['partner/edit-profile']);  // Navigate to edit partner profile page
  }

  onDeleteProfile() {
    if (confirm('Are you sure you want to delete your profile?')) {
      this.partnerService.deletePartner().subscribe(
        () => {
          alert('Profile deleted successfully');
          localStorage.removeItem('partnerId');  // Remove partnerId from local storage after deletion
          this.router.navigate(['partner/login']);  // Redirect to home or login page
        },
        (error) => {
          console.error('Error deleting profile:', error);
        }
      );
    }
  }
}
