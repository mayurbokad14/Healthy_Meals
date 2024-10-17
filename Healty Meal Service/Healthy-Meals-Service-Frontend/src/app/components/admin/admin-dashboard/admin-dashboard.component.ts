import { Component } from '@angular/core';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent {
  totalMeals: number = 500;       // Dummy data
  totalUsers: number = 1200;      // Dummy data
  totalPartners: number = 25;     // Dummy data

  constructor() { }

  ngOnInit(): void {
    // You can later replace this with an API call to fetch real data
  }
}
