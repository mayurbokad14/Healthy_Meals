import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthServiceService } from '../../partner/partner-services/auth-service.service';
import { AuthService } from '../user-service/auth.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent {

  loginForm: FormGroup;
  submitted = false;
  errorMessage = '';

  constructor(private fb: FormBuilder,private router: Router, private authService: AuthService, private toastr: ToastrService) {
    this.loginForm = this.fb.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      // checkMeOut: [false]
    });
  }

  // Method to check if the field is invalid and has been touched
  isInvalidAndTouched(field: string): boolean {
    const control = this.loginForm.get(field);
    return !!control && control.invalid && control.touched;
  }
  

  // Method to handle form submission
  onSubmit() {
    this.submitted = true;

    // Stop if form is invalid
    if (this.loginForm.invalid) {
      return;
    }

    const { username, password } = this.loginForm.value;

    // Call the login method from AuthService
    this.authService.userLogin(username, password).subscribe({
      next: (response) => {
        // Navigate to home/dashboard after successful login
        console.log(response.json);
        console.log(response.userId);
        this.router.navigate(['user/home']);
        this.authService.setUserId(response.user.userId);

        this.toastr.success('Congratulations !!!', ' Login Successful', {
          timeOut: 4000,
          progressBar: true,
          progressAnimation: 'decreasing',
          closeButton: true,
        });
      },
      error: (err) => {
        // Handle errors such as invalid credentials
        this.errorMessage = 'Invalid username or password';
        console.error(err);
        this.toastr.error('Invalid username or password', 'please try again', {
          disableTimeOut: false,
          progressBar: true,
          progressAnimation: 'decreasing',
          closeButton: true,
        });
      }
    });
  }
}
