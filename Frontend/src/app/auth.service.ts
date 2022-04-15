import { Injectable } from '@angular/core';
import { of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  isLogged = false;
  roleAs:string = "";

  constructor() { }

  login(role:string) {
    this.isLogged = true;
    this.roleAs = role;
    localStorage.setItem('STATE','true');
    localStorage.setItem('ROLE',this.roleAs);
    return of({ success: this.isLogged, role:this.roleAs })
  }
  logout() {
    this.isLogged=false;
    this.roleAs = "";
    localStorage.setItem('STATE','false')
    localStorage.setItem('ROLE',"")
    return of({ success:this.isLogged, role:this.roleAs })
  }
  isLoggedIn() {
    return this.isLogged;
  }
  getRole() {
    return localStorage.getItem('ROLE');
  }
}

