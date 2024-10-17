import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../user-service/user.service';
import { Router } from '@angular/router';
import { AuthService } from '../user-service/auth.service';

@Component({
  selector: 'app-user-signup',
  templateUrl: './user-signup.component.html',
  styleUrls: ['./user-signup.component.css']
})
export class UserSignupComponent {
  registerForm!: FormGroup;
  errorMessage: string = '';

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService, // Assuming UserService handles user registration
    private router: Router
  ) {}

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      username: ['', Validators.required],
      userEmail: ['', [Validators.required, Validators.email]],
      phoneNumber: ['', [Validators.required, Validators.minLength(10)]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      address: ['', Validators.required],
      city: ['', Validators.required],
      state: ['', Validators.required],
      zipCode: ['', [Validators.required, Validators.minLength(5)]]
    });
  }

  onSubmit() {
    if (this.registerForm.invalid) {
      return;
    }

    this.authService.registerUser(this.registerForm.value).subscribe({
      next: (response) => {
        console.log('User registered successfully:', response);
        this.router.navigate(['/user/login']);
      },
      error: (err) => {
        console.error('Error during registration:', err);
        this.errorMessage = 'Registration failed. Please try again.';
      }
    });
  }
}
