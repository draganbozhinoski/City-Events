import { getLocaleTimeFormat } from '@angular/common';
import { Quote } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { CityEvent } from 'src/model/CityEvent';
import { CityLocale } from 'src/model/CityLocale';
import { EventsService } from '../events.service';

@Component({
  selector: 'app-events-home',
  templateUrl: './events-home.component.html',
  styleUrls: ['./events-home.component.css']
})
export class EventsHomeComponent implements OnInit {
  events: CityEvent[] = [];
  eventsSub$: Observable<CityEvent[]> = this.service.getEvents();
  eventLocaleMap: Map<Event,String> = new Map<Event,String>()


  constructor(private service: EventsService) { }

  ngOnInit(): void {
    this.eventsSub$.subscribe({
      next: (data) => {
          this.events = data;
      },
      error: (error) => {
          console.log('error', error);
      },
  });
  }

}
