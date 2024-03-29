import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { CityLocale } from 'src/model/CityLocale';
import { EventsService } from '../events.service';
import { Router } from '@angular/router';
import { TokenStorageService } from '../_services/token-storage.service';
import { CityUser } from 'src/model/CityUser';

@Component({
  selector: 'app-locales-form',
  templateUrl: './locales-form.component.html',
  styleUrls: ['./locales-form.component.css']
})
export class LocalesFormComponent implements OnInit {
  localesList: CityLocale[] =[]
  userId:Number|undefined
  usersList:CityUser[]=[]
  private file:File | undefined
  types=["COFFEE_SHOP","LUNCH_BAR","RESTAURANT","NIGHT_CLUB"]
  createEvent = new FormGroup({
    name: new FormControl(null,Validators.required),
    numTables: new FormControl(null,Validators.required),
    logoUrl: new FormControl(null,Validators.required),
    type: new FormControl(null,Validators.required)
  })

  constructor(private service: EventsService,private http:HttpClient,private router:Router,private token:TokenStorageService) { }

  ngOnInit(): void {
    this.service.getUserByUsername(this.token.getUser().username).subscribe({
      next:(data)=>this.userId=data.id
    })
    this.service.getUsers().subscribe({
      next:(data)=>{this.usersList=data}
    })
  }
submit(){
  this.http.post('http://localhost:8082/api/locales/save',{
      "userId":this.userId,
      "name":this.createEvent.controls['name'].value,
      "type":this.createEvent.controls['type'].value,
      "numTables":this.createEvent.controls['numTables'].value,
      "logoUrl":this.createEvent.controls['logoUrl'].value,
    }).subscribe();
    window.location.href = "http://localhost:4200/home"
}
}
