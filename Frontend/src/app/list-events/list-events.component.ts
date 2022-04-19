import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { map, Observable, tap } from 'rxjs';
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

  constructor(private service: EventsService,private sanitizer:DomSanitizer) {}

  ngOnInit(): void {
    this.eventsSub$.pipe(
      map(data => this.transformData(data)),
      tap(data => console.log(data)))
    .subscribe({
      next: (data) => {
        this.events = data;
      },
      error: (error) => {
        console.log('error', error);
      },
    });
  }
  transformData(data:CityEvent[]):CityEvent[] {
    return data.map(t => {
      let url = `data:image/png;base64,${t.image.bytes}`;
      let image = this.sanitizer.bypassSecurityTrustUrl(url);
      return {
        id:t.id,
        name:t.name,
        numReservations:t.numReservations,
        city:t.city,
        adult:t.adult,
        logoUrl:t.logoUrl,
        covidCertificate:t.covidCertificate,
        date:t.date,
        description:t.description,
        locale:t.locale,
        image:image,
      } as CityEvent
    })
  }
}