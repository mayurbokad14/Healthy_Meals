import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PartnerService } from '../partner-services/partner.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-partner-signup',
  templateUrl: './partner-signup.component.html',
  styleUrls: ['./partner-signup.component.css']
})
export class PartnerSignupComponent {
  registerForm!: FormGroup;
  submitted = false;
  errorMessage: string = '';

  constructor(private fb: FormBuilder, private partnerService: PartnerService, private router: Router ) {}

  ngOnInit(): void {
    this.registerForm = this.fb.group({
      partnerName: ['', Validators.required],
      shopName: ['', Validators.required],
      partnerEmail: ['', [Validators.required, Validators.email]],
      phoneNumber: ['', [Validators.required, Validators.pattern('^((\\+91-?)|0)?[0-9]{10}$')]], // Indian phone number pattern, adjust as needed
      password: ['', [Validators.required, Validators.minLength(6)]],
      address: ['', Validators.required],
      city: ['', Validators.required],
      state: ['', Validators.required],
      zipCode: ['', Validators.required],
      shopDescription: [''],
      logoUrl: ['']
    });
  }

  onSubmit() {
    this.submitted = true;

    if (this.registerForm.valid) {
      // Send form data to the backend
      this.partnerService.registerPartner(this.registerForm.value).subscribe(
        response => {
          console.log('Partner registered successfully', response);
          // Redirect to a success or login page after successful registration
          this.router.navigate(['partner/login']);
        },
        error => {
          console.error('Error registering partner', error);
          this.errorMessage = 'Failed to register. Please try again later.';
        }
      );
    } else {
      this.registerForm.markAllAsTouched();
    }
  }  
}
