import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { HomeComponent } from './home/home.component';
import { EventsHomeComponent } from './events-home/events-home.component';
import { ListLocalesComponent } from './list-locales/list-locales.component';
import { FooterComponent } from './footer/footer.component';
import { ListEventsComponent } from './list-events/list-events.component';
import { EventPageComponent } from './event-page/event-page.component';
import { EventFormComponent } from './event-form/event-form.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HomeComponent,
    EventsHomeComponent,
    ListLocalesComponent,
    FooterComponent,
    ListEventsComponent,
    EventPageComponent,
    EventFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
