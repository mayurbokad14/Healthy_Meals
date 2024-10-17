import { Component } from '@angular/core';
import { UserService } from './user.service';
import { User } from '../model/User';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent {
  users: User[] = [];
  pageNumber: number = 0;
  pageSize: number = 10;
  totalPages: number = 0;
  sortBy: string = 'userId';
  sortDir: string = 'desc';

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.fetchUsers();
  }

  fetchUsers(): void {
    
    this.userService.getUsers(this.pageNumber, this.pageSize, this.sortBy, this.sortDir)
      .subscribe(response => {
        this.users = response;
        this.totalPages = Math.ceil(this.users.length / this.pageSize);
        console.log(this.totalPages);
      });
      console.log(this.users);
  }

  sortData(column: string): void {
    this.sortBy = column;
    this.sortDir = this.sortDir === 'asc' ? 'desc' : 'asc';
    this.fetchUsers();
  }

  changePage(page: number): void {
    this.pageNumber = page;
    this.fetchUsers();
  }

  previousPage(): void {
    if (this.pageNumber > 0) {
      this.pageNumber--;
      this.fetchUsers();
    }
  }

  nextPage(): void {
    if (this.pageNumber < this.totalPages - 1) {
      this.pageNumber++;
      this.fetchUsers();
    }
  }

  deleteUserData(userId:number){
    
  }
}
