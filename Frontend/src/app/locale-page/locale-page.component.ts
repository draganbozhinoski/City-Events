import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { filter, map, mergeMap, tap } from 'rxjs';
import { CityLocale } from 'src/model/CityLocale';
import { CityReview } from 'src/model/CityReview';
import { EventsService } from '../events.service';
import { TokenStorageService } from '../_services/token-storage.service';

@Component({
  selector: 'app-locale-page',
  templateUrl: './locale-page.component.html',
  styleUrls: ['./locale-page.component.css']
})
export class LocalePageComponent implements OnInit {
  locale :CityLocale | undefined
  reviews:CityReview[]=[]
  user:any

  constructor(private route: ActivatedRoute,private service: EventsService,private storage:TokenStorageService) {}

  ngOnInit(): void {
    this.user = this.storage.getUser()
    this.route.paramMap.pipe(
      filter(params=>params.has("id")),
      map(params=>+params.get("id")!),
      mergeMap((p)=>this.service.getLocaleById(p))
      )
      .subscribe({
        next: data => {
            this.locale = data
        },
        error: data => {
        }
    })

    this.route.paramMap.pipe(
      filter(params=>params.has("id")),
      map(params=>+params.get("id")!),
      mergeMap((p)=>this.service.getReviewsById(p))
      )
      .subscribe({
        next: data => {
            this.reviews = data
        },
        error: data => {
        }
    })
  }
}
