import { Component } from '@angular/core';
import { CartService } from './cart.service';
import { AuthService } from '../user-service/auth.service';
import { MealService } from '../meal-info/meal.service';
import { Route, Router } from '@angular/router';
import { PaymentService } from '../payment/payment.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent {

  cart: any = {};
  totalPrice: number = 0;

  constructor(private cartService: CartService, private authService: AuthService, private mealService:MealService, private router: Router, private payment: PaymentService) { }

  ngOnInit(): void {
    this.loadCart();
  }

  // Load cart data from service
  loadCart() {
    const userId = this.authService.getUserId();  // Get the logged-in user ID
    this.cartService.getCart(userId).subscribe(
      (cartData) => {
        this.cart = cartData;
        this.calculateTotalPrice();

        // Fetch partner details for each meal in the cart
        this.cart.meals.forEach((meal: any) => {
          this.mealService.getMealPartner(meal.mealId).subscribe(
            (mealPartnerData) => {
              meal.partnerName = mealPartnerData.partnerDTO.partnerName; // Add partner info to the meal
            },
            (error) => {
              console.error('Error fetching meal partner details', error);
            }
          );
        });
      },
      (error) => {
        console.error('Error fetching cart data', error);
      }
    );
  }

  // Increase quantity of meal
  increaseQuantity(mealId: number) {
    this.cartService.addToCart(mealId, this.authService.getUserId()).subscribe(() => {
      this.cart.meals.forEach((meal: { mealId: number; quantity: number; }) => {
        if (meal.mealId === mealId) {
          meal.quantity += 1;
          this.calculateTotalPrice();
        }
      });
    });
  }

  // Decrease quantity of meal
  decreaseQuantity(mealId: number) {
    this.cartService.removeFromCart(mealId, this.authService.getUserId()).subscribe(() => {
      this.cart.meals.forEach((meal: { mealId: number; quantity: number; }) => {
        if (meal.mealId === mealId && meal.quantity > 1) {
          meal.quantity -= 1;
          this.calculateTotalPrice();
        }
      });
    });
  }

  // Remove meal from cart
  removeMeal(mealId: number) {
    this.cartService.removeFromCart(mealId, this.authService.getUserId(), true).subscribe(() => {
      this.cart.meals = this.cart.meals.filter((meal: { mealId: number; }) => meal.mealId !== mealId);
      this.calculateTotalPrice();
    });
  }

  // Calculate total price
  calculateTotalPrice() {
    this.totalPrice = this.cart.meals.reduce((sum: number=0, meal: { price: number; quantity: number; }) => sum + meal.price , 0);
    this.payment.updateTotalAmount(this.totalPrice);
  }

  // Order now action
  orderNow() {
    console.log('Ordering meals with total price:', this.totalPrice);
    this.router.navigateByUrl('user/payment');
  }
}
