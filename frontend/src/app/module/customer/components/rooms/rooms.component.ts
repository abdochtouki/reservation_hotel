import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {NzMessageService} from "ng-zorro-antd/message";
import {NzModalService} from "ng-zorro-antd/modal";
import {CustomerService} from "../../service/customer.service";
import {AdminService} from "../../../admin/admin-services/admin.service";
import {handleCircleGradient} from "ng-zorro-antd/progress/utils";
import {UserStorageService} from "../../../../services/storage/user-storage.service";

@Component({
  selector: 'app-rooms',
  templateUrl: './rooms.component.html',
  styleUrls: ['./rooms.component.css']
})
export class RoomsComponent {

  currentPage: number = 1;
  rooms: any[] = [];
  total: any;
  loading = false;

  constructor(
    private router: Router,
    private message: NzMessageService,
    private customerService: CustomerService,
    private modalService: NzModalService,
  ) {
  }

  ngOnInit(): void {
    this.getRooms();
  }

  getRooms(): void {
    const page = this.currentPage > 0 ? this.currentPage - 1 : 0; // Ensure a valid page number
    this.customerService.getRooms(page).subscribe(
      (data: any) => {
        console.log(data);
        this.rooms = data.roomDtolist;
        this.total = data.totalPages * 1;
      },
    );
  }

  pageIndexChange(value: any) {
    this.currentPage = value;
    this.getRooms();
  }

  isVisibleMiddle=false;
  date: Date[] =[];
  checkInDate: Date;
  checkOutDate: Date;
  id: number;
  onChange(result: Date[]){
    if(result.length ===2){
      this.checkInDate=result[0];
      this.checkOutDate=result[1];
    }
  }

  handleCancelMiddle(){
    this.isVisibleMiddle=false;
  }

  getImageForRoom(type: string): string {
    const imageMap: { [key: string]: string } = {
      Deluxe: '../../../../../assets/deluxe-room.jpeg',
      Double: '../../../../../assets/double-room.jpeg',
      Suite: '../../../../../assets/suite.jpeg',
      Single: '../../../../../assets/single-room.jpg',
      Twin: '../../../../../assets/twin-room.jpeg',
    };
    return imageMap[type] || '../../../../../assets/stodio.jpg';
  }

  handleOkMiddle(){
    const obj = {
      userId: UserStorageService.getUserId(),
      roomId: this.id,
      checkOutDate: this.checkOutDate,
      checkInDate: this.checkInDate,
    };


    this.customerService.bookRoom(obj).subscribe(data=>{
      this.message.
      success("Book added successfully",
        {nzDuration: 5000});
      this.isVisibleMiddle=false;
    },error => {
      this.message.
      error(`${error.error}`,
        {nzDuration: 5000});
      }
    );
  }
  showModalMiddle(id:number){
    this.id=id;
    this.isVisibleMiddle=true;
  }
}
