import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RiWindowsFill } from 'angular-remix-icon';
import { EventsService } from '../events.service';
import { AuthService } from '../_services/auth.service';
import { TokenStorageService } from '../_services/token-storage.service';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {
  login = new FormGroup({
    username: new FormControl(null,Validators.required),
    password: new FormControl(null,Validators.required)
  })
  form:any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles:string[] = [];
  constructor(private router:Router,private service: EventsService,private http:HttpClient,private authService:AuthService,private tokenStorage:TokenStorageService) { }

  ngOnInit(): void {
    if(this.tokenStorage.getToken()) {
        this.isLoggedIn=true;
        this.roles = this.tokenStorage.getUser().role;
    }
  }


  submit(){
    this.form.username = this.login.get('username')!!.value;
    this.form.password = this.login.get('password')!!.value;
    this.authService.login(this.form).subscribe(data => {
      this.tokenStorage.saveToken(data.accessToken);
      this.tokenStorage.saveUser(data);
      this.isLoginFailed = false;
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getUser().role;
      console.log("Logged in as",this.tokenStorage.getUser())
      this.router.navigate(['/home'])
    }, err => {
      this.errorMessage=err.error.errorMessage;
      this.isLoginFailed = true;
    })
  }
    reloadPage() {
      window.location.reload();
    }


    // this.http.post('http://localhost:8082/api/locales/save',{
    //     "localeName":this.login.controls['localeName'].value,
    //     "type":this.login.controls['type'].value,
    //     "numTables":this.login.controls['numTables'].value,
    //     "imageId":this.login.controls['imageId'].value
    //   }).subscribe()


}
