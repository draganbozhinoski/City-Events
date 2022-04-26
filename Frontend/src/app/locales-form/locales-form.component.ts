import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { CityLocale } from 'src/model/CityLocale';
import { EventsService } from '../events.service';
import { Image } from 'src/model/Image';
import { Router } from '@angular/router';
import { finalize } from 'rxjs';

@Component({
  selector: 'app-locales-form',
  templateUrl: './locales-form.component.html',
  styleUrls: ['./locales-form.component.css']
})
export class LocalesFormComponent implements OnInit {
  localesList: CityLocale[] | null = null
  private file:File | null = null
  types=["COFFEE_SHOP","LUNCH_BAR","RESTAURANT","NIGHT_CLUB"]
  createEvent = new FormGroup({
    localeName: new FormControl(null,Validators.required),
    numTables: new FormControl(null,Validators.required),
    logoUrl: new FormControl(null,Validators.required),
    type: new FormControl(null,Validators.required)
  })

  constructor(private service: EventsService,private http:HttpClient,private router:Router) { }

  ngOnInit(): void {
  }

  onFileChange(event:Event) {
    this.file = ((event.target) as HTMLInputElement).files!![0]
    this.createEvent.patchValue({fileSource: this.file})
}
submit(){
  const formData = new FormData();
  // formData.append('file',this.createEvent.get('fileSource')!!.value)
  // this.http.post<Image>('http://localhost:8082/api/images/save',formData).pipe(
  //   map(data => data.id),
  //   switchMap(data => this.http.post('http://localhost:8082/api/events/save',{
  //     "localeName":this.createEvent.controls['localeName'].value,
  //     "numTables":this.createEvent.controls['numTables'].value,
  //     "type":this.createEvent.controls['type'].value,
  //     "imageId":data
  //   }))
  // ).subscribe()
  this.http.post('http://localhost:8082/api/locales/save',{
      "localeName":this.createEvent.controls['localeName'].value,
      "type":this.createEvent.controls['type'].value,
      "numTables":this.createEvent.controls['numTables'].value,
      "imageId":this.createEvent.controls['imageId'].value,
    }).subscribe()//finalize TODO redirect do home
}
}
