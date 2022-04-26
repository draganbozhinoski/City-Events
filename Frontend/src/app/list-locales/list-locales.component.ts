import { Component, OnInit } from '@angular/core';
import { mergeMap, Observable } from 'rxjs';
import { CityLocale } from 'src/model/CityLocale';
import { EventsService } from '../events.service';

@Component({
  selector: 'app-list-locales',
  templateUrl: './list-locales.component.html',
  styleUrls: ['./list-locales.component.css'],
})
export class ListLocalesComponent implements OnInit {
  locales: CityLocale[] = [];
  review:Number|undefined
  localesSub$: Observable<CityLocale[]> = this.service.getLocales();

  constructor(private service: EventsService) {}

  ngOnInit(): void {
    this.localesSub$.subscribe({
      next: (data) => {
        this.locales = data;
      },
      error: (error) => {
        console.log('error', error);
      },
    });
  }
}
