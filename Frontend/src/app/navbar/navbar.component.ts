import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../_services/auth.service';
import { TokenStorageService } from '../_services/token-storage.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  user:any
  constructor(private storage:TokenStorageService,private router:Router) { }
  showAdminBoard = false;
  showAddEvent = false;

  ngOnInit(): void {
    this.user = this.storage.getUser()
    if(this.user){
    this.showAdminBoard = this.user.type == 'ROLE_ADMIN'
    this.showAddEvent = this.user.type == 'ROLE_OWNER'
    }
  }

  logOut(){
    this.storage.signOut()
    this.user=undefined
    this.router.navigate([""])
  }
}
