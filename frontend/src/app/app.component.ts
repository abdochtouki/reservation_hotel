import { Component } from '@angular/core';
import {UserStorageService} from "./services/storage/user-storage.service";
import {AuthService} from "./services/auth/auth.service";
import {NavigationEnd, Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'hotel_reservation_front';
  isCustomerLoggedIn : boolean=UserStorageService.isCustomerLoggedIn();
  isAdminLoggedIn : boolean=UserStorageService.isAdminLoggedIn();
    constructor(private router: Router,) {
    }
    ngOnInit() {
      this.router.events.subscribe((event) => {
        if(event.constructor.name === 'NavigationEnd') {
          this.isCustomerLoggedIn = UserStorageService.isCustomerLoggedIn();
          this.isAdminLoggedIn = UserStorageService.isAdminLoggedIn();
        }
      })
    }
    logout() {
      UserStorageService.signOut();
      this.router.navigateByUrl('/')
    }

}
