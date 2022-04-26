import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { EventsService } from '../events.service';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {

  constructor(private service:EventsService,private router:Router) { }

  createUser = new FormGroup({
      name:new FormControl(null,Validators.required),
      email:new FormControl(null,Validators.required),
      password:new FormControl(null,Validators.required),
      phoneNumber:new FormControl(null,Validators.required),
      username:new FormControl(null,Validators.required),
      role:new FormControl(null,Validators.required)
    })

  ngOnInit(): void {
  }
  submit() {
    // /api/auth/register
    this.service.saveUser(this.createUser.get('name')!!.value,this.createUser.get('username')!!.value,this.createUser.get('email')!!.value,
                  this.createUser.get('password')!!.value,this.createUser.get('phoneNumber')!!.value,this.createUser.get('role')!!.value)
                  .subscribe(data => {
                    console.log(data),
                    window.parent.location.href = "http://localhost:4200/home";
                  });
  }

}
