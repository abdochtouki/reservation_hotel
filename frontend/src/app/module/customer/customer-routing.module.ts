import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CustomerComponent } from './customer.component';
import {RoomsComponent} from "./components/rooms/rooms.component";
import {VieuBookingsComponent} from "./components/vieu-bookings/vieu-bookings.component";

const routes: Routes = [
  { path: '', component: CustomerComponent },
   { path: 'rooms', component: RoomsComponent },
   { path: 'bookings', component: VieuBookingsComponent },
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
  ],
  exports: [RouterModule]
})
export class CustomerRoutingModule { }
