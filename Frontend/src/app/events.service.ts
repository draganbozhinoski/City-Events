import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CityEvent } from 'src/model/CityEvent';
import { CityLocale } from 'src/model/CityLocale';
import { SingleEvent } from 'src/model/SigleEvent';
import { SingleLocale } from 'src/model/SingleLocale';

@Injectable({
  providedIn: 'root'
})
export class EventsService {

  constructor(private http: HttpClient) { 
  }

  getEvents(): Observable<CityEvent[]> {
    return this.http.get<CityEvent[]>(
      "http://localhost:8082/api/events"
    );
  }

  getEventById(id:Number): Observable<SingleEvent> {
    return this.http.get<SingleEvent>(
      `http://localhost:8082/api/events/${id}`
    );
  }
  getLocaleById(id:Number): Observable<SingleLocale> {
    return this.http.get<SingleLocale>(
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
