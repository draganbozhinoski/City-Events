import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.css']
})
export class AdminPanelComponent implements OnInit {
  iFrameUrl:any
  constructor(private domSanitizer:DomSanitizer) { }

  ngOnInit(): void {
    this.iFrameUrl = this.domSanitizer.bypassSecurityTrustResourceUrl('/admin/events');
  }
  showEvents() {
    this.iFrameUrl = this.domSanitizer.bypassSecurityTrustResourceUrl('/admin/events');
  }
  showLocales() {
    this.iFrameUrl = this.domSanitizer.bypassSecurityTrustResourceUrl('/admin/locales');
  }
  showUsers() {
    this.iFrameUrl = this.domSanitizer.bypassSecurityTrustResourceUrl('/admin/users');
  }
  showReservations() {
    this.iFrameUrl = this.domSanitizer.bypassSecurityTrustResourceUrl('/admin/reservations');
  }
  showReviews() {
    this.iFrameUrl = this.domSanitizer.bypassSecurityTrustResourceUrl('/admin/reviews');
  }
}
