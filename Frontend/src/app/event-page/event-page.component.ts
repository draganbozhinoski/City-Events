import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { filter, map, mergeMap, switchMap, tap } from 'rxjs';
import { CityEvent } from 'src/model/CityEvent';
import { EventsService } from '../events.service';

@Component({
  selector: 'app-event-page',
  templateUrl: './event-page.component.html',
  styleUrls: ['./event-page.component.css']
})
export class EventPageComponent implements OnInit {
  event :CityEvent | undefined;
  id:Number = -1
  success:Boolean=false
  
  constructor(private route: ActivatedRoute,private service: EventsService,private sanitizer:DomSanitizer) {}

  ngOnInit(): void {
    //Dali vaka treba da se napravi
    this.route.paramMap.pipe(
      filter(params=>params.has("id")),
      map(params=>+params.get("id")!),
      mergeMap(p=>this.service.getEventById(p)),
      map(data => this.transformData(data)),
      tap(data => console.log(data))
      )
      .subscribe({
        next: data => {
          console.log(data)
          this.event = data
        },
        error: data => {
            
        }
      });
  }
  transformData(data:CityEvent):CityEvent {
    let url = `data:image/png;base64,${data.image.bytes}`;
    let image = this.sanitizer.bypassSecurityTrustUrl(url);
    return {
        id:data.id,
        name:data.name,
        numReservations:data.numReservations,
        city:data.city,
        adult:data.adult,
        logoUrl:data.logoUrl,
        covidCertificate:data.covidCertificate,
        date:data.date,
        description:data.description,
        locale:data.locale,
        image:image
      } as CityEvent
    }

    reserve(){
      console.log("test")
      this.service.reserve(1).subscribe(data=>this.success=data)

    }

}
