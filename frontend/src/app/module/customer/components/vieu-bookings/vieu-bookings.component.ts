import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {CustomerService} from "../../service/customer.service";
import {NzMessageService} from "ng-zorro-antd/message";

@Component({
  selector: 'app-vieu-bookings',
  templateUrl: './vieu-bookings.component.html',
  styleUrls: ['./vieu-bookings.component.css']
})
export class VieuBookingsComponent {

  currentPage:any=1;
  total:any;
  bookings:any;
  constructor(
    private router:Router,
    private customerService: CustomerService,
    private message: NzMessageService,
  ) {
    this.getBookings();
  }
  getBookings() {
    this.customerService.getMyBookings(this.currentPage - 1).subscribe(
      data => {
        console.log('API response:', data);  // Pour vérifier la réponse
        this.bookings = data.reservations;
        this.total = data.totalPages * 5;  // Vous pouvez aussi utiliser totalPages directement si l'API renvoie le nombre total d'éléments
        console.log('Total pages:', this.total);  // Vérification de la valeur
      },
      error => {
        this.message.error(`${error.error}`, {nzDuration: 5000});
      }
    );
  }

  pageIndexChange(value:any){
    this.currentPage=value;
    this.getBookings();
  }
}
