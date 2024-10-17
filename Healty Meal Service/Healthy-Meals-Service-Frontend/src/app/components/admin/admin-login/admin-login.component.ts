import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthServiceService } from '../../partner/partner-services/auth-service.service';
import { Router } from '@angular/router';
import { AdminService } from '../admin-service/admin.service';
import { AdminAuthService } from '../admin-service/admin-auth.service';

@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.css'],
})
export class AdminLoginComponent {
  loginForm: FormGroup;
  submitted = false;
  errorMessage = '';

  constructor(
    private formBuilder: FormBuilder,
    private authService: AdminAuthService, // Inject AuthService
    private router: Router
  ) {
    this.loginForm = this.formBuilder.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      rememberMe: [false],
    });
  }

  get f() {
    return this.loginForm.controls;
  }

  onSubmit() {
    this.submitted = true;

    // Stop if form is invalid
    if (this.loginForm.invalid) {
      return;
    }

    const { username, password } = this.loginForm.value;

    // Call the admin login method from AuthService
    this.authService.adminLogin(username, password).subscribe({
      next: (response) => {
        console.log('JWT Token received:', response);
        // Navigate to the admin dashboard after successful login
        this.router.navigate(['admin/dashboard']);
      },
      error: (err) => {
        // Handle errors such as invalid credentials
        this.errorMessage = 'Invalid username or password';
        console.error(err);
      }
    });
  }
}
