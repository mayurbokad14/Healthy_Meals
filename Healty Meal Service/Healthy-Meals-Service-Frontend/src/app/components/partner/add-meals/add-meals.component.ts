import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { PartnerService } from '../partner-services/partner.service';

@Component({
  selector: 'app-add-meals',
  templateUrl: './add-meals.component.html',
  styleUrls: ['./add-meals.component.css']
})
export class AddMealsComponent {
  mealForm!: FormGroup;
  mealId!: number;
  isEditMode = false;


  constructor(
    private fb: FormBuilder,
    private partnerService: PartnerService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    // Initialize the form
    this.mealForm = this.fb.group({
      mealName: ['', Validators.required],
      description: ['', Validators.required],
      price: [null, [Validators.required, Validators.min(0)]],
      category: ['', Validators.required],
      imageUrl: [''],
      available: [false]
    });

    this.route.paramMap.subscribe(params => {
      this.mealId = +params.get('mealId')!;  // Get the mealId from the route
      if (this.mealId) {
        this.isEditMode = true;
        this.loadMealData(this.mealId);  // Fetch and populate the meal data for editing
      }
    });

  }


  loadMealData(mealId: number): void {
    this.partnerService.getMealById(mealId).subscribe(
      meal => {
        this.mealForm.patchValue(meal);  // Populate the form with meal data
      },
      error => {
        console.error('Error fetching meal data', error);
      }
    );
  }


  // Method to handle form submission
  onSubmit(): void {
    if (this.mealForm.valid) {
      const mealData = this.mealForm.value;

      if (this.isEditMode) {
        // Update the meal if in edit mode
        this.partnerService.updateMeal(this.mealId, mealData).subscribe(
          response => {
            console.log('Meal updated successfully!', response);
            this.router.navigate(['/meals']);  // Navigate back to meals listing after update
          },
          error => {
            console.error('Error updating meal!', error);
          }
        );
      } else {
        // Add a new meal if not in edit mode
        const partnerId = 1;  // Replace with dynamic partner ID
        this.partnerService.addMeal(partnerId, mealData).subscribe(
          response => {
            console.log('Meal added successfully!', response);
            this.router.navigate(['/meals']);  // Navigate back to meals listing after adding
          },
          error => {
            console.error('Error adding meal!', error);
          }
        );
      }
    }  
  }
}
