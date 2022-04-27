import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminEventsComponent } from './admin-events/admin-events.component';
import { AdminLocalesComponent } from './admin-locales/admin-locales.component';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import { AdminReservationsComponent } from './admin-reservations/admin-reservations.component';
import { AdminReviewsComponent } from './admin-reviews/admin-reviews.component';
import { AdminUsersComponent } from './admin-users/admin-users.component';
// import { AuthGuard } from './auth.guard';
import { CreateUserComponent } from './create-user/create-user.component';
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
import { ReviewFormComponent } from './review-form/review-form.component';

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent},
  { path: 'locales/create', component: LocalesFormComponent },
  { path: 'locales/:id', component: LocalePageComponent},
  { path: 'events/create', component: EventFormComponent},
  { path: 'login', component: LoginFormComponent},
  { path: 'profile/:id', component: ProfilePageComponent},
  { path: 'register', component: RegisterFormComponent},
  { path: 'events/:id', component: EventPageComponent},
  { path: 'events', component: ListEventsComponent},
  { path: 'users/create', component: CreateUserComponent},
  { path: 'locales/:id', component: LocalePageComponent},
  { path: 'locales', component: ListLocalesComponent},
  { path: 'reviews/create', component: ReviewFormComponent},
  { path: 'admin/events', component: AdminEventsComponent},
  { path: 'admin/locales', component: AdminLocalesComponent},
  { path: 'admin/users', component: AdminUsersComponent},
  { path: 'admin/reservations', component: AdminReservationsComponent},
  { path: 'admin/reviews', component: AdminReviewsComponent},
  { path: 'admin', component: AdminPanelComponent},
  { path: '**', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
