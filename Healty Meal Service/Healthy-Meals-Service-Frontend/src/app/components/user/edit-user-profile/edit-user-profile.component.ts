import { Component } from '@angular/core';
import { UserService } from '../user-service/user.service';
import { Router } from '@angular/router';
import { User } from '../model/User';

@Component({
  selector: 'app-edit-user-profile',
  templateUrl: './edit-user-profile.component.html',
  styleUrls: ['./edit-user-profile.component.css']
})
export class EditUserProfileComponent {
  user: User = {
    userId: 0,
    username: '',
    userEmail: '',
    phoneNumber: '',
    password: '',
    address: '',
    city: '',
    state: '',
    zipCode: '',
    createdAt: ''
  };

  constructor(private userService: UserService, private router: Router) {}

  ngOnInit(): void {
    // Fetch the current user details to populate the form
    this.userService.getUserById().subscribe(
      (data: User) => {
        this.user = data;
      },
      (error) => {
        console.error('Error fetching user data:', error);
      }
    );
  }

  onSubmit() {
    // Update the user information
    this.userService.updateUser(this.user).subscribe(
      (updatedUser: User) => {
        this.router.navigate(['user/login']); // Redirect to the profile page after successful update
      },
      (error) => {
        console.error('Error updating profile:', error);
      }
    );
  }
}
