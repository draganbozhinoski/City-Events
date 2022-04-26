import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { filter, map, mergeMap } from 'rxjs';
import { User } from 'src/model/User';
import { EventsService } from '../events.service';

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.css']
})
export class ProfilePageComponent implements OnInit {
  user:User|undefined
  constructor(private route: ActivatedRoute,private service: EventsService) { }

  ngOnInit(): void {
    this.route.paramMap.pipe(
      filter(params=>params.has("id")),
      map(params=>+params.get("id")!),
      mergeMap((p)=>this.service.getUserById(p))
      )
      .subscribe({
        next: data => {
            this.user = data
        },
        error: data => {
            
        }
    })

    this.route.paramMap.pipe(
      filter(params=>params.has("id")),
      map(params=>+params.get("id")!),
      mergeMap((p)=>this.service.getLocaleByOwnerId(p))
      )
      .subscribe({
        next: data => {
          
            this.user!.locale = data.name
            console.log(this.user)
        },
        error: data => {
            
        }
    })
  }

}
