import { getLocaleTimeFormat } from '@angular/common';
import { Quote } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { CityEvent } from 'src/model/CityEvent';
import { EventsService } from './events.service';

@Component({
  selector: 'app-events-home',
  templateUrl: './events-home.component.html',
  styleUrls: ['./events-home.component.css']
})
export class EventsHomeComponent implements OnInit {
  events: CityEvent[] = [
    {"id" :1,
    "name":"One",
    "numReservations":1,
    "city":"String",
    "adult":true,
    "covidCertificate":true,
    "date":1,
    "locale":1
  },
  {"id" :1,
    "name":"Two",
    "numReservations":1,
    "city":"String",
    "adult":true,
    "covidCertificate":true,
    "date":1,
    "locale":1
  },
  {"id" :1,
    "name":"Three",
    "numReservations":1,
    "city":"String",
    "adult":true,
    "covidCertificate":true,
    "date":1,
    "locale":1
  },
  {"id" :1,
    "name":"Four",
    "numReservations":1,
    "city":"String",
    "adult":true,
    "covidCertificate":true,
    "date":1,
    "locale":1
  },
  {"id" :1,
    "name":"Five",
    "numReservations":1,
    "city":"String",
    "adult":true,
    "covidCertificate":true,
    "date":1,
    "locale":1
  },
  {"id" :1,
    "name":"Six",
    "numReservations":1,
    "city":"String",
    "adult":true,
    "covidCertificate":true,
    "date":1,
    "locale":1
  },
  {"id" :1,
    "name":"Seven",
    "numReservations":1,
    "city":"String",
    "adult":true,
    "covidCertificate":true,
    "date":1,
    "locale":1
  }
];
  eventsSub$: Observable<CityEvent[]> = this.service.getEvents();


  constructor(private service: EventsService) { }

  ngOnInit(): void {
  //   this.eventsSub$.subscribe({
  //     next: (data) => {
  //         this.events = data;
  //     },
  //     error: (error) => {
  //         console.log('error', error);
  //     },
  // });

  }

}
