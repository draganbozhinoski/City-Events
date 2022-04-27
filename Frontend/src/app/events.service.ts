import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CityEvent } from 'src/model/CityEvent';
import { CityLocale } from 'src/model/CityLocale';

import { SingleLocale } from 'src/model/SingleLocale';
import { EventImage } from 'src/model/EventImage';
import { User } from 'src/model/User';
import { CityUser } from 'src/model/CityUser';
import { CityReservation } from 'src/model/CityReservation';
import { CityReview } from 'src/model/CityReview';


@Injectable({
  providedIn: 'root'
})
export class EventsService {

  constructor(private http: HttpClient) { 
  }

  getEventImages(): Observable<EventImage[]> {
    return this.http.get<EventImage[]>(
      "/api/events/eventImages"
    );
  }
  getEvents(): Observable<CityEvent[]> {
    return this.http.get<CityEvent[]>("/api/events");
  }
  getEventById(id:Number): Observable<CityEvent> {
    return this.http.get<CityEvent>(
      `/api/events/${id}`
    );
  }
  getLocaleById(id:Number): Observable<CityLocale> {
    return this.http.get<CityLocale>(
      `/api/locales/${id}`
    );
  }
  getLocales(): Observable<CityLocale[]> {
    return this.http.get<CityLocale[]>(
      "/api/locales"
    );
  }
  getUsers(): Observable<CityUser[]> {
    return this.http.get<CityUser[]>(
      "/api/users"
    )
  }
  getReservations(): Observable<CityReservation[]> {
    return this.http.get<CityReservation[]>(
      "/api/reservations"
    )
  }
  getReviews(): Observable<CityReview[]> {
    return this.http.get<CityReview[]>(
      "/api/reviews"
    )
  }
  deleteEvent(eventId:Number):Observable<CityEvent[]> {
    return this.http.delete<CityEvent[]>(`api/events/delete/${eventId}`);
  }
  deleteLocale(localeId:Number):Observable<CityLocale[]> {
    return this.http.delete<CityLocale[]>(`api/locales/delete/${localeId}`);
  }
  deleteReview(reviewId:Number):Observable<CityReview[]> {
    return this.http.delete<CityReview[]>(`api/locales/delete/${reviewId}`);
  }
  deleteUser(userId:Number):Observable<CityUser[]> {
    return this.http.delete<CityUser[]>(`/api/users/delete/${userId}`);
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
  saveUser(
    name: String,
    username: String,
    email: String,
    password: String,
    phoneNumber: String,
    role:String
  ) {
    return this.http.post<any>("/api/auth/register", {
      "name":name,
      "username":username,
      "email":email,
      "password":password,
      "phoneNumber":phoneNumber,
      "role":role
    })
  }
  reserve(id:Number): Observable<Boolean> {
    return this.http.get<Boolean>(
      `http://localhost:8082/api/locales/${id}/reserve`
    );
  }

  getReview(id:Number): Observable<Number> {
    return this.http.get<Number>(
      `http://localhost:8082/api/locales/${id}/ratings`
    );
  }


getUserById(id:Number): Observable<User> {
  return this.http.get<User>(
    `http://localhost:8082/api/users/${id}`
  );
}

getLocaleByOwnerId(id:Number): Observable<CityLocale> {
  return this.http.get<CityLocale>(
    `http://localhost:8082/api/locales/owner/${id}`
  );
}
}
