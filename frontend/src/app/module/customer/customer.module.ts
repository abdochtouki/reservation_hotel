import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CustomerRoutingModule } from './customer-routing.module';
import { CustomerComponent } from './customer.component';
import { RoomsComponent } from './components/rooms/rooms.component';
import {DemoNgZorroAntdModule} from "../../DemoNgZorroAntdModule";
import {FormsModule} from "@angular/forms";
import { VieuBookingsComponent } from './components/vieu-bookings/vieu-bookings.component';


@NgModule({
  declarations: [
    CustomerComponent,
    RoomsComponent,
    VieuBookingsComponent,
  ],
  imports: [
    CommonModule,
    CustomerRoutingModule,
    DemoNgZorroAntdModule,
    FormsModule,
  ]
})
export class CustomerModule { }
