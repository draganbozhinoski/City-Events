import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EventFormComponent } from './event-form/event-form.component';
import { EventPageComponent } from './event-page/event-page.component';
import { HomeComponent } from './home/home.component';
import { ListEventsComponent } from './list-events/list-events.component';
import { ListLocalesComponent } from './list-locales/list-locales.component';
import { LocalesFormComponent } from './locales-form/locales-form.component';

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent},
  { path: 'locales/create', component: LocalesFormComponent},
  { path: 'locales', component: ListLocalesComponent},
  { path: 'events/create', component: EventFormComponent},
  { path: 'events/:id', component: EventPageComponent},
  { path: 'events', component: ListEventsComponent},
  { path: '**', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
