import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { PaymentService } from './payment.service';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit{
  totalAmount!: number;  // Example amount

  @ViewChild('paymentRef', {static: true}) paymentRef!: ElementRef;

  constructor(private router: Router, private payment: PaymentService) {
    this.totalAmount=payment.totalAmount;
   }

  ngOnInit(): void {
      window.paypal.Buttons(
        {
          style:{
            layout:'horizontal',
            color:'blue',
            shape:'rect',
            label:'paypal'
          },
          createOrder: (data: any, action: any) => {
            return action.order.create({
              purchase_units:[
                {
                  amount: {
                    value: this.totalAmount.toString(),
                    currency_code: 'USD',
                  },
                }
              ]
            });
          },
          onApprove:(data: any, actions: any) => {
            return actions.order.capture().then((details: any) =>{
              console.log(details);
              if(details.status === 'COMPLETED'){

                this.payment.transactionID= details.id;
                this.router.navigate(['user/confirmation']);
              }
            })
          },
          onError: (error: any) =>{
            console.log(error);
          }
        }
      ).render(this.paymentRef.nativeElement);
  }

  cancelPayment() {
    // Logic to cancel the payment
    console.log("Payment cancelled.");
  }

  processPaypalPayment() {
    // Logic to handle PayPal payment
    console.log("Processing PayPal payment...");
  }
}
