import {Component} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {AuthService} from "../../../services/auth/auth.service";
import {NzMessageService} from "ng-zorro-antd/message";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  registerForm!: FormGroup;

  constructor(private fb: FormBuilder,
              private authService: AuthService,
              private message :NzMessageService,
              private router: Router,

  ) {
  }
  ngOnInit() {
    this.registerForm = this.fb.group({
      email: [null, [Validators.required, Validators.email]],
      password: [null,Validators.required],
      name:[null,Validators.required],
    })
  }
   submitForm() {
    this.authService.register(this.registerForm.value).subscribe(data=>{
      if(data.id!=null){
        this.message.success("Sign up successfully.", {nzDuration:5000});
        this.router.navigateByUrl('/');
      }else{
        this.message.error(`${data.message}`, {nzDuration:5000});

      }
    },error => {
      this.message.error(`${error.error}`,{nzDuration:5000});
      }
    );
   }
}
