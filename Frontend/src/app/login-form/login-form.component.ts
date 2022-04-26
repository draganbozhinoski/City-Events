import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { EventsService } from '../events.service';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {
  login = new FormGroup({
    localeName: new FormControl(null,Validators.required),
    numTables: new FormControl(null,Validators.required)
  })

  constructor(private service: EventsService,private http:HttpClient) { }

  ngOnInit(): void {
  }

  submit(){
    this.http.post('http://localhost:8082/api/locales/save',{
        "localeName":this.login.controls['localeName'].value,
        "type":this.login.controls['type'].value,
        "numTables":this.login.controls['numTables'].value,
        "imageId":this.login.controls['imageId'].value
      }).subscribe()
  }

}
