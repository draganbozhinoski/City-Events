import { Component, HostListener, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { EventsService } from '../events.service';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { map, switchMap, tap } from 'rxjs';
import { Image } from 'src/model/Image';


@Component({
  selector: 'app-event-form',
  templateUrl: './event-form.component.html',
  styleUrls: ['./event-form.component.css']
})
export class EventFormComponent implements OnInit {
  // name :String |undefined
  // numReservations :Number |undefined
  // city :String |undefined
  // date :Date |undefined
  // eventImage :File |undefined
  // adult :Boolean |undefined
  // covidCertificate :Boolean |undefined
  // localeId :Number |undefined
  // logoUrl :String |undefined
  private file:File | null = null
  createEvent = new FormGroup({
    eventName: new FormControl(null,Validators.required),
    numReservations: new FormControl(null,Validators.required),
    city: new FormControl(null,Validators.required),
    date: new FormControl(null,Validators.required),
    eventImage: new FormControl(null,Validators.required),
    adult: new FormControl(null,Validators.required),
    covidCertificate: new FormControl(null,Validators.required),
    localeId: new FormControl(null,Validators.required),
    logoUrl: new FormControl(null,Validators.required),
    fileSource: new FormControl(null,Validators.required),
    description: new FormControl(null,Validators.required)
  })

  constructor(private service: EventsService,private http:HttpClient) { }

  ngOnInit(): void {
    // this.createEvent.valueChanges.subscribe(
    //   data => console.log(data.eventImage)
    // )
  }
  onFileChange(event:Event) {
      this.file = ((event.target) as HTMLInputElement).files!![0]
      this.createEvent.patchValue({fileSource: this.file})
  }

  submit(){
    const formData = new FormData();
    formData.append('file',this.createEvent.get('fileSource')!!.value)
    this.http.post<Image>('http://localhost:8082/api/images/save',formData).pipe(
      map(data => data.id),
      switchMap(data => this.http.post('http://localhost:8082/api/events/save',{
        "name":this.createEvent.controls['eventName'].value,
        "numReservations":this.createEvent.controls['numReservations'].value,
        "city":this.createEvent.controls['city'].value,
        "adult":this.createEvent.controls['adult'].value,
        "covidCertificate":this.createEvent.controls['covidCertificate'].value,
        "date":this.createEvent.controls['date'].value,
        "localeId":this.createEvent.controls['localeId'].value,
        "description":this.createEvent.controls['description'].value,
        "logoUrl":this.createEvent.controls['logoUrl'].value,
        "imageId":data
      }))
    ).subscribe()
      // this.service.SaveEvent(
      //   this.name,
      //   this.numReservations,
      //   this.city,
      //   this.date,
      //   this.eventImage,
      //   this.adult,
      //   this.covidCertificate,
      //   this.localeId,
      //   this.logoUrl,
      // ).subscribe()
  }

}
