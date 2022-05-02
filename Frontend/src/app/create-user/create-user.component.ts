import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { EventsService } from '../events.service';
import { AuthService } from '../_services/auth.service';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {

  constructor(private service:EventsService,private router:Router,private authService:AuthService) { }

  createUser = new FormGroup({
      name:new FormControl(null,Validators.required),
      email:new FormControl(null,Validators.required),
      password:new FormControl(null,Validators.required),
      phoneNumber:new FormControl(null,Validators.required),
      username:new FormControl(null,Validators.required),
      role:new FormControl(null,Validators.required)
    })
    form:any = {}
    isSuccessfull = true;
    isSignupFailed = false;
    errorMessage = ''

  ngOnInit(): void {
  }
  submit() {
    // /api/auth/register
    this.form.name = this.createUser.get('name')!!.value
    this.form.username = this.createUser.get('username')!!.value
    this.form.email = this.createUser.get('email')!!.value
    this.form.password = this.createUser.get('password')!!.value
    this.form.phoneNumber = this.createUser.get('phoneNumber')!!.value
    this.form.role = this.createUser.get('role')!!.value

    console.log(this.form)
    this.authService.register(this.form).subscribe(data => {
      console.log(data);
      this.isSuccessfull = true;
      this.isSignupFailed = false;
      window.parent.location.href = "http://localhost:4200/admin";
    }, err => {
      this.errorMessage=err.error.errorMessage
      this.isSignupFailed=true;
    })
  }

}
