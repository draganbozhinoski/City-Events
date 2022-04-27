import { Component, OnInit } from '@angular/core';
import { AuthService } from '../_services/auth.service';
import { TokenStorageService } from '../_services/token-storage.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private storage:TokenStorageService) { }
  showAdminBoard = false;

  ngOnInit(): void {
    const user = this.storage.getUser()
    console.log(user.type)
    this.showAdminBoard = user.type == 'ROLE_ADMIN' || user.type == 'ROLE_OWNER'
  }

}
