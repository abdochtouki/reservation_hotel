import { Component } from '@angular/core';
import {NzMessageService} from "ng-zorro-antd/message";
import {AdminService} from "../../admin-services/admin.service";

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.css']
})
export class ReservationsComponent {
  currentPage = 1;
  total: any;
  reservations: any;
  constructor(
    private message: NzMessageService,
    private adminService: AdminService,
  ) {
    this.getReservations();
  }
  getReservations(): void {
    this.adminService.getReservations(this.currentPage-1).subscribe(
      data => {
        console.log(data);
        this.reservations=data.reservations;
        this.total = data.totalPages * 5;
      }
    )
  }
  pageIndexChange(value:any){
    this.currentPage = value;
    this.getReservations();
  }
  changeReservationStatus(bookingId:number,status:String){
    this.adminService.changeReservationStatus(bookingId,status).subscribe(
      data=>{
        this.message.success(`Reservation status updated successfully.`,{nzDuration:5000});
        this.getReservations();
      },error => {
        this.message.error(`${error.error}`,{nzDuration:5000})
      }
    )
  }

}
