import {Component} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AdminService} from "../../admin-services/admin.service";
import {NzMessageService} from "ng-zorro-antd/message";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-update-room',
  templateUrl: './update-room.component.html',
  styleUrls: ['./update-room.component.css']
})
export class UpdateRoomComponent {
  updateRoomForm: FormGroup;
  id = this.activatedroute.snapshot.params['id'];

  constructor(public fb: FormBuilder,
              private router: Router,
              private message: NzMessageService,
              private adminService: AdminService,
              private activatedroute: ActivatedRoute) {
    this.updateRoomForm = this.fb.group({
      name: ['', Validators.required],
      type: ['', Validators.required],
      price: ['', Validators.required],
    });
    this.getRoomById();
  }

  submitForm() {
    if (this.updateRoomForm.invalid) {
      this.message.error("Please correct the form errors.", {nzDuration: 5000});
      return;
    }

    this.adminService.updateRoomDetails(this.id, this.updateRoomForm.value).subscribe(
      data => {
        this.message.success("Room updated successfully", {nzDuration: 5000});
        this.router.navigateByUrl("/admin/dashboard");
      },
      error => {
        this.message.error(error?.error || 'An unexpected error occurred', {nzDuration: 5000});
      }
    );
  }

  getRoomById() {
    this.adminService.getRoomById(this.id).subscribe(
      data => {
        this.updateRoomForm.patchValue({data})
      }, error => {
        this.message.error(`${error.error}`, {nzDuration: 5000})
      }
    )
  }


}
