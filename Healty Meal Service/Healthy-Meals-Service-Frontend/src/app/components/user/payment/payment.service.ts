import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  totalAmount = 0;
  transactionID = "";
  constructor() { }


  updateTotalAmount(amount: number){
    this.totalAmount = amount;
  }
}
