import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserLoginComponent } from './components/user/user-login/user-login.component';
import { PartnerLoginComponent } from './components/partner/partner-login/partner-login.component';
import { UserHomeComponent } from './components/user/user-home/user-home.component';
import { MainComponent } from './components/user/main/main.component';
import { MealsComponent } from './components/user/meals/meals.component';
import { PartnerHomeComponent } from './components/partner/partner-home/partner-home.component';
import { MainPartnerComponent } from './components/partner/main-partner/main-partner.component';
import { PartnerMealsComponent } from './components/partner/partner-meals/partner-meals.component';
import { AddMealsComponent } from './components/partner/add-meals/add-meals.component';
import { PartnerSignupComponent } from './components/partner/partner-signup/partner-signup.component';
import { CartComponent } from './components/user/cart/cart.component';
import { MealInfoComponent } from './components/user/meal-info/meal-info.component';
import { AuthGuard } from './components/user/gaurd-interceptor/auth.guard';
import { PartnerGuardGuard } from './components/partner/guard-interceptor/partner-guard.guard';
import { PaymentComponent } from './components/user/payment/payment.component';
import { UserSignupComponent } from './components/user/user-signup/user-signup.component';
import { AdminLoginComponent } from './components/admin/admin-login/admin-login.component';
import { AdminDashboardComponent } from './components/admin/admin-dashboard/admin-dashboard.component';
import { AdminMainComponent } from './components/admin/admin-main/admin-main.component';
import { UserListComponent } from './components/admin/user-list/user-list.component';
import { PartnerListComponent } from './components/admin/partner-list/partner-list.component';
import { UserProfileComponent } from './components/user/user-profile/user-profile.component';
import { EditUserProfileComponent } from './components/user/edit-user-profile/edit-user-profile.component';
import { PartnerProfileComponent } from './components/partner/partner-profile/partner-profile.component';
import { EditPartnerProfileComponent } from './components/partner/edit-partner-profile/edit-partner-profile.component';
import { ConfirmationComponent } from './components/user/confirmation/confirmation.component';

const routes: Routes = [
  {
    path: 'user/login',
    component: UserLoginComponent,
  },
  {
    path: 'user/signup',
    component: UserSignupComponent,
  },
  {
    path: '',
    redirectTo: 'user/login',
    pathMatch: 'full',
  },
  {
    path: '',
    component: MainComponent,
    children: [
      {
        path: 'user/home',
        component: UserHomeComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'user/meals',
        component: MealsComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'user/cart',
        component: CartComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'user/mealinfo/:mealId',
        component: MealInfoComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'user/profile',
        component: UserProfileComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'user/edit-profile',
        component: EditUserProfileComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'user/payment',
        component: PaymentComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'user/confirmation',
        component: ConfirmationComponent,
        canActivate: [AuthGuard]
      },
    ],
  },
  {
    path: 'partner/login',
    component: PartnerLoginComponent,
  },
  {
    path: 'partner/signup',
    component: PartnerSignupComponent,
  },
  {
    path: 'partner',
    redirectTo: 'partner/home',
    pathMatch: 'full',
  },
  {
    path: '',
    component: MainPartnerComponent,
    children: [
      {
        path: 'partner/home',
        component: PartnerHomeComponent,
        canActivate: [PartnerGuardGuard]
      },
      {
        path: 'partner/meals',
        component: PartnerMealsComponent,
        canActivate: [PartnerGuardGuard]
      },
      {
        path: 'partner/addmeals',
        component: AddMealsComponent,
        canActivate: [PartnerGuardGuard]
      },
      { 
        path: 'meals/edit/:mealId',
        component: AddMealsComponent,
        canActivate: [PartnerGuardGuard]
      },
      { 
        path: 'partner/profile',
        component: PartnerProfileComponent,
        canActivate: [PartnerGuardGuard]
      },
      { 
        path: 'partner/edit-profile',
        component: EditPartnerProfileComponent,
        canActivate: [PartnerGuardGuard]
      },
    ],
  },
  {
    path: 'admin/login',
    component: AdminLoginComponent,
  },
  {
    path: 'admin',
    redirectTo: 'admin/home',
    pathMatch: 'full',
  },
  {
    path: '',
    component: AdminMainComponent,
    children: [
      {
        path: 'admin/dashboard',
        component: AdminDashboardComponent,
      },
      {
        path: 'admin/userlist',
        component: UserListComponent,
      },
      {
        path: 'admin/meallist',
        component: AdminDashboardComponent,
      },
      {
        path: 'admin/partnerlist',
        component: PartnerListComponent,
      },

      
    ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
