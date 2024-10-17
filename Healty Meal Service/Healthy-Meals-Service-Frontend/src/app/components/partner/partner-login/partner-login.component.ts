import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthServiceService } from '../partner-services/auth-service.service';
@Component({
  selector: 'app-partner-login',
  templateUrl: './partner-login.component.html',
  styleUrls: ['./partner-login.component.css'],
})
export class PartnerLoginComponent {
  loginForm: FormGroup;
  submitted = false;
  errorMessage = '';

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthServiceService,  // Inject AuthService
    private router: Router
  ) {
    this.loginForm = this.formBuilder.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required, Validators.minLength(6)]],
    });
  }

  get f() { return this.loginForm.controls; }

  onSubmit() {
    this.submitted = true;

    // Stop if form is invalid
    if (this.loginForm.invalid) {
      return;
    }

    const { username, password } = this.loginForm.value;

    // Call the login method from AuthService
    this.authService.partnerLogin(username, password).subscribe({
      next: (response) => {
        // Navigate to home/dashboard after successful login
        this.router.navigate(['partner/home']);
        this.authService.setPartnerId(response.user.partnerId);
      },
      error: (err) => {
        // Handle errors such as invalid credentials
        this.errorMessage = 'Invalid username or password';
        console.error(err);
      }
    });
  }

  // navigateToHome() {
  //   console.log('okok');
  //   this.router.navigate(['/partner']);
  // }
}
