import { Component, OnInit } from '@angular/core';
import { PartnerService } from '../partner-services/partner.service';
import { Router } from '@angular/router';
import { AuthServiceService } from '../partner-services/auth-service.service';

@Component({
  selector: 'app-partner-meals',
  templateUrl: './partner-meals.component.html',
  styleUrls: ['./partner-meals.component.css']
})
export class PartnerMealsComponent implements OnInit{

  meals: any[] = [];  // This will hold the meals data

  constructor(private partnerService: PartnerService, private router:Router,  private authService: AuthServiceService ) { }

  ngOnInit(): void {
    this.displayAllMeal();
  }

  displayAllMeal(){
    const partnerId = this.authService.getPartnerId();  // Retrieve dynamic partner ID


    // Fetch meals from the backend
    this.partnerService.getMeals(partnerId).subscribe(
      (data) => {
        this.meals = data;
      },
      (error) => {
        console.error('Error fetching meals:', error);
      }
    );
  }

  deleteMealData(mealId: number){
    this.partnerService.deleteMeal(mealId).subscribe({
      next:(res: any) =>{
        this.displayAllMeal();
      },
      error: console.log,
    })
  }

  onEditMeal(mealId: number): void {
    this.router.navigate(['/meals/edit', mealId]);  // Navigates to the edit form
  }


}
