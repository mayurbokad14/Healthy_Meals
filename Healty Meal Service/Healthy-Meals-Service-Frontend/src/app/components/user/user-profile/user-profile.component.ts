import { Component } from '@angular/core';
import { UserService } from '../user-service/user.service';
import { Router } from '@angular/router';
import { User } from '../model/User';


@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent {
  user: User | undefined;

  constructor(private userService: UserService, private router: Router) {}

  ngOnInit(): void {
    // Fetch user details by ID (from local storage)
    this.userService.getUserById().subscribe(
      (data: User) => {
        this.user = data;
      },
      (error) => {
        console.error('Error fetching user data:', error);
      }
    );
  }

  onEditProfile() {
    this.router.navigate(['user/edit-profile']); // Navigate to the Edit Profile page
  }

  onDeleteProfile() {
    if (confirm('Are you sure you want to delete your profile?')) {
      this.userService.deleteUser().subscribe(
        () => {
          alert('Profile deleted successfully');
          localStorage.removeItem('userId'); // Remove user ID from local storage after deletion
          this.router.navigate(['user/login']); // Redirect to home or login page
        },
        (error) => {
          console.error('Error deleting profile:', error);
        }
      );
    }
  }
}
