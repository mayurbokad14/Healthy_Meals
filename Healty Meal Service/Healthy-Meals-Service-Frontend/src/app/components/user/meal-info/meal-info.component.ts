import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CartService } from '../cart/cart.service';
import { MealService } from './meal.service';
import { AuthService } from '../user-service/auth.service';

@Component({
  selector: 'app-meal-info',
  templateUrl: './meal-info.component.html',
  styleUrls: ['./meal-info.component.css']
})
export class MealInfoComponent {
  meal: any = {};

  constructor(
    private route: ActivatedRoute,
    private mealService: MealService,
    private cartService: CartService,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    const mealId = this.route.snapshot.params['mealId'];
    this.loadMealDetails(mealId);
  }

  // Load meal details from service
  loadMealDetails(mealId: number) {
    this.mealService.getMealPartner(mealId).subscribe(
      (data) => {
        this.meal = data;
      },
      (error) => {
        console.error('Error fetching meal details', error);
      }
    );
  }

  // Add meal to cart
  addToCart(mealId: number) {
    const userId = this.authService.getUserId();  // Assuming user ID is stored in localStorage
    this.cartService.addToCart(mealId, userId).subscribe(
      () => {
        console.log('Meal added to cart');
      },
      (error) => {
        console.error('Error adding meal to cart', error);
      }
    );
  }
}
