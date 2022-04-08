import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { CityLocale } from 'src/model/CityLocale';
import { LocalesService } from './locales.service';

@Component({
  selector: 'app-list-locales',
  templateUrl: './list-locales.component.html',
  styleUrls: ['./list-locales.component.css'],
})
export class ListLocalesComponent implements OnInit {
  locales: CityLocale[] = [];
  localesSub$: Observable<CityLocale[]> = this.service.getLocales();

  constructor(private service: LocalesService) {}

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
