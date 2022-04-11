import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CityLocale } from 'src/model/CityLocale';

@Injectable({
  providedIn: 'root'
})
export class LocalesService {

  constructor(private http: HttpClient) { 
  }

  getLocales(): Observable<CityLocale[]> {
    return this.http.get<CityLocale[]>(
      "http://localhost:8082/api/locales"
    );
  }
}
