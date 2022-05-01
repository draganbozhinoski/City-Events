import { Component, OnInit } from '@angular/core';
import { CityReservation } from 'src/model/CityReservation';
import { EventsService } from '../events.service';

@Component({
  selector: 'app-admin-reservations',
  templateUrl: './admin-reservations.component.html',
  styleUrls: ['./admin-reservations.component.css']
})
export class AdminReservationsComponent implements OnInit {
  reservations:CityReservation[] = []
  constructor(private service:EventsService) { }

  ngOnInit(): void {
    this.service.getReservations().subscribe(data => this.reservations = data);
  }
  delete(id:Number){
    this.service.deleteReservation(id).subscribe(data => this.reservations = data)
  }
}
