import { Component, OnInit } from '@angular/core';
import { PaymentService } from '../payment/payment.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-confirmation',
  templateUrl: './confirmation.component.html',
  styleUrls: ['./confirmation.component.css']
})
export class ConfirmationComponent implements OnInit{
  transactionId ="";
  constructor(private payment: PaymentService, private router: Router){}

  ngOnInit(): void {
      this.transactionId = this.payment.transactionID;
  }

  goBackHome() {
    this.router.navigate(['user/home']);
  }
}
