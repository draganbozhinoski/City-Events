import { Component, OnInit } from '@angular/core';
import { CityEvent } from 'src/model/CityEvent';
import { EventsService } from '../events.service';

@Component({
  selector: 'app-admin-events',
  templateUrl: './admin-events.component.html',
  styleUrls: ['./admin-events.component.css'],
})
export class AdminEventsComponent implements OnInit {
  events: CityEvent[] = [];
  constructor(private service: EventsService) {}

  ngOnInit(): void {
    this.service.getEvents().subscribe((data) => (this.events = data));
  }
  delete(event: CityEvent) {
    this.service
      .deleteEvent(event.id)
      .subscribe((data) => (this.events = data));
  }
}
