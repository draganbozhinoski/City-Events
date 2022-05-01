import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { filter, map, tap } from 'rxjs';
import { EventsService } from '../events.service';
import { TokenStorageService } from '../_services/token-storage.service';

@Component({
  selector: 'app-reserve-form',
  templateUrl: './reserve-form.component.html',
  styleUrls: ['./reserve-form.component.css']
})
export class ReserveFormComponent implements OnInit {
  eventId:Number|undefined
  reservation=new FormGroup({
    date: new FormControl(null,Validators.required),
    description: new FormControl(null,Validators.required)
  })
  

  constructor(private route: ActivatedRoute,private router: Router,private service: EventsService,private http:HttpClient,private token:TokenStorageService) { }

  ngOnInit(): void {
    this.route.paramMap.pipe(
      filter(params=>params.has("id")),
      map(params=>+params.get("id")!)
      ).subscribe(data=>this.eventId=data);
  }

  submit(){
    this.service.reserve(
      this.eventId!,
      this.token.getUser().name,
      this.reservation.controls["date"].value,
      this.token.getUser().username,
      this.token.getUser().phoneNumber,
      this.reservation.controls["description"].value
    ).subscribe()
    this.router.navigateByUrl(`events/${this.eventId}`)
  }

}
