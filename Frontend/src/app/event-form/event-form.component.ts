import { Component, Input, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { LocalesService } from '../list-locales/locales.service';

@Component({
  selector: 'app-event-form',
  templateUrl: './event-form.component.html',
  styleUrls: ['./event-form.component.css']
})
export class EventFormComponent implements OnInit {
  name :String |undefined
  numReservations :Number |undefined
  city :String |undefined
  date :Date |undefined
  eventImage :File |undefined
  adult :Boolean |undefined
  covidCertificate :Boolean |undefined
  localeId :Number |undefined
  logoUrl :String |undefined

  constructor(private service: LocalesService) { }

  ngOnInit(): void {
  }

  submit(){
      this.service.SaveEvent(
        this.name ,
        this.numReservations,
        this.city ,
        this.date ,
        this.eventImage,
        this.adult,
        this.covidCertificate,
        this.localeId,
        this.logoUrl,
      ).subscribe()
  }

}
