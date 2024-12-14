import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../../services/auth/auth.service";
import {Router} from "@angular/router";
import {NzMessageService} from "ng-zorro-antd/message";
import {UserStorageService} from "../../../services/storage/user-storage.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm!: FormGroup;
  constructor(private authService: AuthService,
              private router: Router,
              private message:NzMessageService,
              private fb: FormBuilder,) {
  }
  ngOnInit() {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]]
    })
  }
  submitForm() {
    this.authService.login(this.loginForm.value).subscribe(data=>{
      console.log(data)
      if(data.userId != null){
        const user={
          id:data.userId,
          role:data.userrole,
        }
        UserStorageService.saveUser(user);
        UserStorageService.saveToken(data.jwt);
        if(UserStorageService.isAdminLoggedIn()){
          this.router.navigateByUrl('/admin/dashboard');
        }else if(UserStorageService.isCustomerLoggedIn()){
          this.router.navigateByUrl('/customer/rooms');
        }
      }
      this.message.success("Login Successfully.", {nzDuration:5000});
    },error => {
      this.message.error("Login failed." ,{nzDuration:5000});
    })
  }

}
