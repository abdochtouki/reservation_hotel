import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {AdminService} from '../../admin-services/admin.service';
import {NzMessageService} from 'ng-zorro-antd/message';
import {NzModalService} from "ng-zorro-antd/modal";
import {id_ID} from "ng-zorro-antd/i18n";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent implements OnInit {
  currentPage: number = 1;
  rooms: any[] = [];
  total: any;
  loading = false;

  constructor(
    private router: Router,
    private message: NzMessageService,
    private adminService: AdminService,
    private modalService: NzModalService,
  ) {
  }

  ngOnInit(): void {
    this.getRooms();
  }

  getRooms(): void {
    const page = this.currentPage > 0 ? this.currentPage - 1 : 0; // Ensure a valid page number
    this.adminService.getRooms(page).subscribe(
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
 showConfirm(id: number) {
    this.modalService.confirm({
      nzTitle: `confirm `,
      nzContent: `Do you want to delete this room ?`,
      nzOkText: `delete`,
      nzOnOk: () => {
        this.deleteRoom(id)
      }
    })
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

  deleteRoom(id: number) {
    this.adminService.deleteRoom(id).subscribe(data => {
      this.message.success("Room Deleted Successfully", {nzDuration: 5000});
      this.getRooms();
      this.router.navigateByUrl('/admin/dashboard');
    }, error => {
      this.message.error(`${error.error}`, {nzDuration: 5000});
    });
  }

  protected readonly id_ID = id_ID;
}
