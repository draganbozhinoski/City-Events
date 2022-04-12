import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { CityEvent } from 'src/model/CityEvent';
import { EventsService } from '../events.service';

@Component({
  selector: 'app-list-events',
  templateUrl: './list-events.component.html',
  styleUrls: ['./list-events.component.css']
})
export class ListEventsComponent implements OnInit {
  events: CityEvent[] = [];
  eventsSub$: Observable<CityEvent[]> = this.service.getEvents();

  constructor(private service: EventsService) {}

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
