import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {NzMessageService} from "ng-zorro-antd/message";
import {AdminService} from "../../admin-services/admin.service";

@Component({
  selector: 'app-post-room',
  templateUrl: './post-room.component.html',
  styleUrls: ['./post-room.component.css']
})
export class PostRoomComponent {

  roomDetails: FormGroup;
  constructor(public fb: FormBuilder,
              private router: Router,
              private message :NzMessageService,
              private adminService: AdminService,) {
    this.roomDetails = this.fb.group({
      name:['',Validators.required],
      type:['',Validators.required],
      price: ['',Validators.required],
    })
  }
  submitForm(){
    this.adminService.postRoomDetails(this.roomDetails.value).subscribe(
      data=>{
      this.message.success("Success",{nzDuration: 5000});
      this.router.navigateByUrl('/admin/dashboard');
      },error => {
        this.message.error(`${error.error}`,{nzDuration: 5000})
      }
    )

  }

}
