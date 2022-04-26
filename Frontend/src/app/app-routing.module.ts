import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth.guard';
import { EventFormComponent } from './event-form/event-form.component';
import { EventPageComponent } from './event-page/event-page.component';
import { HomeComponent } from './home/home.component';
import { ListEventsComponent } from './list-events/list-events.component';
import { ListLocalesComponent } from './list-locales/list-locales.component';
import { LocalePageComponent } from './locale-page/locale-page.component';
import { LocalesFormComponent } from './locales-form/locales-form.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { ProfilePageComponent } from './profile-page/profile-page.component';
import { RegisterFormComponent } from './register-form/register-form.component';

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent},
  { 
    path: 'locales/create', component: LocalesFormComponent,
    canActivate: [AuthGuard],
    data: {
      role: 'ADMIN'
    }
  },
  { path: 'locales', component: ListLocalesComponent},
  { 
    path: 'events/create', component: EventFormComponent,
    canActivate: [AuthGuard],
    data: {
      role: 'ADMIN'
    }
  },
  { path: 'events/:id', component: EventPageComponent},
  { path: 'login', component: LoginFormComponent},
  { path: 'profile/:id', component: ProfilePageComponent},
  { path: 'register', component: RegisterFormComponent},
  { path: 'locales/:id', component: LocalePageComponent},
  { path: 'events', component: ListEventsComponent},
  { path: '**', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
