import { Component } from '@angular/core';

@Component({
  selector: 'app-partner-home',
  templateUrl: './partner-home.component.html',
  styleUrls: ['./partner-home.component.css']
})
export class PartnerHomeComponent {
  imageUrl = 'https://i.insider.com/607e4eac74da0300181e2a16?width=700';  // Replace with an actual image of a restaurant or dish
  buttonText = 'Add Dishes';
}
