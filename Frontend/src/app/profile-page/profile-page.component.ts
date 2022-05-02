import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { filter, map, mergeMap } from 'rxjs';
import { CityLocale } from 'src/model/CityLocale';
import { User } from 'src/model/User';
import { EventsService } from '../events.service';
import { TokenStorageService } from '../_services/token-storage.service';

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.css'],
})
export class ProfilePageComponent implements OnInit {
  user: User | undefined;
  constructor(
    private route: ActivatedRoute,
    private service: EventsService,
    private token: TokenStorageService
  ) {}
  currentUser: any;
  locale: CityLocale | undefined;
  ngOnInit(): void {
    this.currentUser = this.token.getUser();
    console.log(this.currentUser);
    this.service.getLocaleByOwnerUsername(this.currentUser.username).subscribe({
      next: (data) => {
        this.locale = data;
      },
      error: (data) => {},
    });
  }
}
