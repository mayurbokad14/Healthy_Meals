import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserLoginComponent } from './components/user/user-login/user-login.component';

import { FormControl, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { PartnerLoginComponent } from './components/partner/partner-login/partner-login.component';
import { NavbarComponent } from './components/user/navbar/navbar.component';
import { MainComponent } from './components/user/main/main.component';
import { MealsComponent } from './components/user/meals/meals.component';
import { PartnerHomeComponent } from './components/partner/partner-home/partner-home.component';
import { MainPartnerComponent } from './components/partner/main-partner/main-partner.component';
import { PartnerMealsComponent } from './components/partner/partner-meals/partner-meals.component';
import { PartnerNavbarComponent } from './components/partner/partner-navbar/partner-navbar.component';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes  } from '@angular/router';
import { AuthServiceService } from './components/partner/partner-services/auth-service.service';
import { AddMealsComponent } from './components/partner/add-meals/add-meals.component';
import { PartnerSignupComponent } from './components/partner/partner-signup/partner-signup.component';
import { CartComponent } from './components/user/cart/cart.component';
import { MealInfoComponent } from './components/user/meal-info/meal-info.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';


import { ToastrModule } from 'ngx-toastr';
import { PaymentComponent } from './components/user/payment/payment.component';
import { ConfirmationComponent } from './components/user/confirmation/confirmation.component';
import { UserSignupComponent } from './components/user/user-signup/user-signup.component';
import { AdminDashboardComponent } from './components/admin/admin-dashboard/admin-dashboard.component';
import { AdminLoginComponent } from './components/admin/admin-login/admin-login.component';
import { AdminMainComponent } from './components/admin/admin-main/admin-main.component';
import { AdminNavComponent } from './components/admin/admin-nav/admin-nav.component';
import { EditPartnerProfileComponent } from './components/partner/edit-partner-profile/edit-partner-profile.component';
import { EditUserProfileComponent } from './components/user/edit-user-profile/edit-user-profile.component';
import { UserProfileComponent } from './components/user/user-profile/user-profile.component';
import { PartnerProfileComponent } from './components/partner/partner-profile/partner-profile.component';
import { UserListComponent } from './components/admin/user-list/user-list.component';

@NgModule({
  declarations: [
    AppComponent,
    UserLoginComponent,
    PartnerLoginComponent,
    MainComponent,
    NavbarComponent,
    MealsComponent,
    PartnerHomeComponent,
    MainPartnerComponent,
    PartnerMealsComponent,
    PartnerNavbarComponent,
    AddMealsComponent,
    PartnerSignupComponent,
    CartComponent,
    MealInfoComponent,
    PaymentComponent,
    ConfirmationComponent,
    UserSignupComponent,
    AdminDashboardComponent,
    AdminLoginComponent,
    AdminMainComponent,
    AdminNavComponent,
    EditPartnerProfileComponent,
    EditUserProfileComponent,
    UserProfileComponent,
    PartnerProfileComponent,
    UserListComponent
    
   
  ],
  imports: [
    BrowserModule,
    CommonModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule,
    RouterModule,
    BrowserAnimationsModule, // required animations module
    ToastrModule.forRoot({
      positionClass :'toast-bottom-right'
    }), 
  ],
  providers: [AuthServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
