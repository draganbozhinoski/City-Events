import { Component, OnInit } from '@angular/core';
import { CityLocale } from 'src/model/CityLocale';
import { EventsService } from '../events.service';

@Component({
  selector: 'app-admin-locales',
  templateUrl: './admin-locales.component.html',
  styleUrls: ['./admin-locales.component.css'],
})
export class AdminLocalesComponent implements OnInit {
  locales: CityLocale[] = [];
  constructor(private service: EventsService) {}
  ngOnInit(): void {
    this.service.getLocales().subscribe((data) => (this.locales = data));
  }
  delete(locale: CityLocale) {
    this.service
      .deleteLocale(locale.id)
      .subscribe((data) => (this.locales = data));
  }
}
