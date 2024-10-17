import { Component } from '@angular/core';
import { UserService } from '../user-service/user.service';
import { Router } from '@angular/router';
import { AuthService } from '../user-service/auth.service';
import { CartService } from '../cart/cart.service';
import { MealService } from '../meal-info/meal.service';

@Component({
  selector: 'app-meals',
  templateUrl: './meals.component.html',
  styleUrls: ['./meals.component.css'],
})
export class MealsComponent {
  meals: any[] = []; // This will hold the meals data
  meal: any = {};
  constructor(
    private cartService: CartService,
    private userService: UserService,
    private router: Router,
    private authService: AuthService,
    private mealService: MealService
  ) {}

  ngOnInit(): void {
    this.displayAllMeal();
  }

  displayAllMeal() {

    // Fetch meals from the backend
    this.userService.getMeals().subscribe(
      (data) => {
        this.meals = data.map((meal: any) => ({
          ...meal,
          inCart: false // Initialize the inCart status for each meal
        }));
      },
      (error) => {
        console.error('Error fetching meals:', error);
      }
    );
  }

  toggleCart(meal: any): void {
    const userId = this.authService.getUserId();
    if (meal.inCart) {
      
      // Remove from cart
      this.cartService.removeFromCart(meal.mealId, userId).subscribe(
        () => {
          meal.inCart = false; // Update UI after successful removal
        },
        (error) => {
          console.error('Error removing meal from cart:', error);
        }
      );
    } else {
      // Add to cart
      console.log(meal.mealId, userId);
      this.cartService.addToCart(meal.mealId, userId).subscribe(
        () => {
          meal.inCart = true; // Update UI after successful addition
        },
        (error) => {
          console.error('Error adding meal to cart:', error);
        }
      );
    }
  }  

  navigateToMealInfo(mealId: number){
    this.router.navigate(['user/mealinfo/'+mealId]);
  }

}
