import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { PostRoomComponent } from './components/post-room/post-room.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NzFormModule} from "ng-zorro-antd/form";
import {NzInputModule} from "ng-zorro-antd/input";
import {NzButtonModule} from "ng-zorro-antd/button";
import {DemoNgZorroAntdModule} from "../../DemoNgZorroAntdModule";
import { UpdateRoomComponent } from './components/update-room/update-room.component';
import { ReservationsComponent } from './components/reservations/reservations.component';
import {NzPaginationModule} from "ng-zorro-antd/pagination";
import {NzTagModule} from "ng-zorro-antd/tag";
import {NzTableModule} from "ng-zorro-antd/table";


@NgModule({
  declarations: [
    AdminComponent,
    DashboardComponent,
    PostRoomComponent,
    UpdateRoomComponent,
    ReservationsComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    ReactiveFormsModule,
    NzFormModule,
    NzInputModule,
    NzButtonModule,
    DemoNgZorroAntdModule,
    FormsModule,
    NzTableModule,
    NzTagModule,
    NzPaginationModule,
  ]
})
export class AdminModule { }
