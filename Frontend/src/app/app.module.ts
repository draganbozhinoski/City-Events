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
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LocalesFormComponent } from './locales-form/locales-form.component';
import { LocalePageComponent } from './locale-page/locale-page.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { RegisterFormComponent } from './register-form/register-form.component';
import { ProfilePageComponent } from './profile-page/profile-page.component';

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
    EventFormComponent,
    LocalesFormComponent,
    LocalePageComponent,
    LoginFormComponent,
    RegisterFormComponent,
    ProfilePageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
