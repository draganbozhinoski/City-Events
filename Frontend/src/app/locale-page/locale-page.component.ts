import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { filter, map, mergeMap } from 'rxjs';
import { CityLocale } from 'src/model/CityLocale';
import { EventsService } from '../events.service';

@Component({
  selector: 'app-locale-page',
  templateUrl: './locale-page.component.html',
  styleUrls: ['./locale-page.component.css']
})
export class LocalePageComponent implements OnInit {
  locale :CityLocale | undefined ;

  constructor(private route: ActivatedRoute,private service: EventsService) {}

  ngOnInit(): void {
    this.route.paramMap.pipe(
      filter(params=>params.has("id")),
      map(params=>+params.get("id")!),
      mergeMap((p)=>this.service.getLocaleById(p))
      )
      .subscribe({
        next: data => {
            this.locale = data.locale
            console.log(data)
        },
        error: data => {
            
        }
    })

}
}
