import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { filter, map, mergeMap } from 'rxjs';
import { CityEvent } from 'src/model/CityEvent';
import { EventsService } from '../list-events/events.service';

@Component({
  selector: 'app-event-page',
  templateUrl: './event-page.component.html',
  styleUrls: ['./event-page.component.css']
})
export class EventPageComponent implements OnInit {
  event :CityEvent | undefined ;
  
  constructor(private route: ActivatedRoute,private service: EventsService) {}

  ngOnInit(): void {
    this.route.paramMap.pipe(
      filter(params=>params.has("id")),
      map(params=>+params.get("id")!),
      mergeMap(p=>this.service.getEvent(p))
      )
      .subscribe({
        next: data => {
            this.event = data
            console.log(this.event)
        },
        error: data => {
            
        }
    }

    );
  }

}
