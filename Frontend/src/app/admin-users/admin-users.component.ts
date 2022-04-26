import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CityUser } from 'src/model/CityUser';
import { EventsService } from '../events.service';

@Component({
  selector: 'app-admin-users',
  templateUrl: './admin-users.component.html',
  styleUrls: ['./admin-users.component.css']
})
export class AdminUsersComponent implements OnInit {
  users:CityUser[] = []
  constructor(private service:EventsService,private router:Router) { }

  ngOnInit(): void {
      this.service.getUsers().subscribe(data => this.users = data);
  }
  // delete(user:CityUser) {
  //   this.service.deleteUser(user.id).subscribe(data => this.users = data);
  // }

}
