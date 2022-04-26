import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-review-form',
  templateUrl: './review-form.component.html',
  styleUrls: ['./review-form.component.css']
})
export class ReviewFormComponent implements OnInit {

  
  createReview = new FormGroup({
    review: new FormControl(null,Validators.required),
    stars: new FormControl(null,Validators.required)
  })

  constructor() { }

  ngOnInit(): void {
  }

  submit() {
      console.log(this.createReview.get('stars')!!.value)
  }

}
