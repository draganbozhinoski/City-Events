import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CityEvent } from 'src/model/CityEvent';
import { CityLocale } from 'src/model/CityLocale';

import { SingleLocale } from 'src/model/SingleLocale';
import { EventImage } from 'src/model/EventImage';


@Injectable({
  providedIn: 'root'
})
export class EventsService {

  constructor(private http: HttpClient) { 
  }

  getEventImages(): Observable<EventImage[]> {
    return this.http.get<EventImage[]>(
      "http://localhost:8082/api/events/eventImages"
    );
  }
  getEvents(): Observable<CityEvent[]> {
    return this.http.get<CityEvent[]>("http://localhost:8082/api/events");
  }
  getEventById(id:Number): Observable<CityEvent> {
    return this.http.get<CityEvent>(
      `http://localhost:8082/api/events/${id}`
    );
  }
  getLocaleById(id:Number): Observable<CityLocale> {
    return this.http.get<CityLocale>(
      `http://localhost:8082/api/locales/${id}`
    );
  }
  getLocales(): Observable<CityLocale[]> {
    return this.http.get<CityLocale[]>(
      "http://localhost:8082/api/locales"
    );
  }
  SaveEvent(
    name: String | undefined,
    numReservations: Number | undefined,
    city: String | undefined,
    date: Date | undefined,
    eventImage: File | undefined,
    adult: Boolean | undefined,
    covidCertificate: Boolean | undefined,
    localeId: Number | undefined,
    logoUrl: String | undefined
  ): Observable<CityEvent> {
    return this.http.post<CityEvent>("http://localhost:8082/api/events/save", {
      "name":name,
      "numReservations":numReservations,
      "city":city,
      "date":date,
      "eventImage":eventImage,
      "adult":adult,
      "covidCertificate":covidCertificate,
      "localeId":localeId
    }
  );
}
}
