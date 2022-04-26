import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {

  constructor() { }

  createUser = new FormGroup({
      name:new FormControl(null,Validators.required),
      email:new FormControl(null,Validators.required),
      password:new FormControl(null,Validators.required),
      phoneNumber:new FormControl(null,Validators.required),
      username:new FormControl(null,Validators.required)
    })

  ngOnInit(): void {
  }
  submit() {
    console.log("Submit.. ")
    console.log(this.createUser.get('name')!!.value,"Name")
  }

}
