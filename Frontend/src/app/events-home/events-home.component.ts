import { getLocaleTimeFormat } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Quote } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { map, Observable } from 'rxjs';
import { CityEvent } from 'src/model/CityEvent';
import { CityLocale } from 'src/model/CityLocale';
import { EventsService } from '../events.service';
import { Image } from 'src/model/Image';
import { EventImage } from 'src/model/EventImage';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-events-home',
  templateUrl: './events-home.component.html',
  styleUrls: ['./events-home.component.css']
})
export class EventsHomeComponent implements OnInit {
  events: EventImage[] = [];
  eventsSub$: Observable<EventImage[]> = this.service.getEventImages();

  constructor(private service: EventsService,private http:HttpClient,private sanitizer:DomSanitizer) { }

  ngOnInit(): void {
    this.eventsSub$.pipe(
      map(data => this.transformData(data))
    )
    .subscribe({
      next: (data) => {
        this.events = data
      },
      error: (error) => {
          console.log('error', error);
      },
    });
  }
  transformData(data:EventImage[]):EventImage[] {
    return data.map(t => {
      let url = `data:image/png;base64,${t.image.bytes}`;
      let image = this.sanitizer.bypassSecurityTrustUrl(url);
      return {
        eventId: t.eventId,
        image: image,
        adult: t.adult,
        covidCertificate: t.covidCertificate,
      } as EventImage
    })
  }

}
