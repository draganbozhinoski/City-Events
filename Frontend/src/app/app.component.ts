import { Component } from '@angular/core';
import { TokenStorageService } from './_services/token-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'city-events';
  private role:string|undefined;
  isLoggedIn = false;
  showAdminBoard = false;
  username:String = ""
  constructor(private tokenStorageService:TokenStorageService) { }
  ngOnInit():void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    if(this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.role = user.role;
      this.showAdminBoard = this.role=='ROLE_ADMIN';
      this.username = user.username
    }
  }
  logout() {
    this.tokenStorageService.signOut();
    window.location.reload();
  }
  
}
