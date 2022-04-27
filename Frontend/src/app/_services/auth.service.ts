import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

const AUTH_API = 'http://localhost:8082/api/auth/'
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http:HttpClient) { }

  login(credentials:any):Observable<any> {
    console.log("Povikan service");
    return this.http.post(AUTH_API + 'login', {
      username: credentials.username,
      password: credentials.password
    }, httpOptions)
  }
  register(user:any):Observable<any> {
    return this.http.post(AUTH_API + 'register', {
      name: user.name,
      username: user.username,
      email: user.email,
      password: user.password,
      phoneNumber: user.phoneNumber,
      role:user.role
    },httpOptions)
  }
}

