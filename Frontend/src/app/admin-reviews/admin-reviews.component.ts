import { Component, OnInit } from '@angular/core';
import { CityReview } from 'src/model/CityReview';
import { EventsService } from '../events.service';

@Component({
  selector: 'app-admin-reviews',
  templateUrl: './admin-reviews.component.html',
  styleUrls: ['./admin-reviews.component.css']
})
export class AdminReviewsComponent implements OnInit {
  reviews:CityReview[] = []
  constructor(private service:EventsService) { }

  ngOnInit(): void {
      this.service.getReviews().subscribe(data => this.reviews=data)
  }

}
