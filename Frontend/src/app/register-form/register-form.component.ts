import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { EventsService } from '../events.service';
import { MustMatch } from '../Validator';

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent implements OnInit {

  login = new FormGroup({
    name: new FormControl('',Validators.required),
    username: new FormControl('',Validators.required),
    localeName: new FormControl('',Validators.required),
    password: new FormControl('',[Validators.required]),
    confirmPassword: new FormControl('',Validators.required),
    phoneNumber: new FormControl('',Validators.required),
    email: new FormControl('',[Validators.email,Validators.required])
  },
  // validator: MustMatch('password', 'confirmPassword')
  );

  constructor(private service: EventsService,private http:HttpClient) { }

  ngOnInit(): void {
  }

  submit(){
    // this.http.post('http://localhost:8082/api/locales/save',{
    //     "localeName":this.login.controls['localeName'].value,
    //     "type":this.login.controls['type'].value,
    //     "numTables":this.login.controls['numTables'].value,
    //     "imageId":this.login.controls['imageId'].value
    //   }).subscribe()
  }

}
