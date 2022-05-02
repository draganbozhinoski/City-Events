import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { filter, map } from 'rxjs';
import { EventsService } from '../events.service';

@Component({
  selector: 'app-review-form',
  templateUrl: './review-form.component.html',
  styleUrls: ['./review-form.component.css'],
})
export class ReviewFormComponent implements OnInit {
  localeId: Number | undefined;

  createReview = new FormGroup({
    review: new FormControl(null, Validators.required),
    stars: new FormControl(null, Validators.required),
  });

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: EventsService,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    this.route.paramMap
      .pipe(
        filter((params) => params.has('id')),
        map((params) => +params.get('id')!)
      )
      .subscribe((data) => (this.localeId = data));
  }

  submit() {
    console.log(this.createReview.get('stars')!!.value);
    this.service
      .addReview(
        this.localeId!,
        this.createReview.controls['review'].value,
        this.createReview.controls['stars'].value
      )
      .subscribe();
    this.router.navigateByUrl(`locales/${this.localeId}`);
  }
}
