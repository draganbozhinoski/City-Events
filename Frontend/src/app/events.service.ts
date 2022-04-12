import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CityEvent } from 'src/model/CityEvent';
import { CityLocale } from 'src/model/CityLocale';
import { SingleEvent } from 'src/model/SigleEvent';

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
  getLocales(): Observable<CityLocale[]> {
    return this.http.get<CityLocale[]>(
      "http://localhost:8082/api/locales"
    );
  }
}
