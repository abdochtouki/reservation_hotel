import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {RegisterComponent} from "./auth/componets/register/register.component";
import {LoginComponent} from "./auth/componets/login/login.component";

const routes: Routes = [
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  { path: 'customer', loadChildren: () => import('./module/customer/customer.module').then(m => m.CustomerModule) },
  { path: 'admin', loadChildren: () => import('./module/admin/admin.module').then(m => m.AdminModule) },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
