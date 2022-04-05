import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CityEvent } from 'src/model/CityEvent';
import { Observable } from 'rxjs';

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

}
