import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { RegisterComponent } from './components/register/register.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { UserhomeComponent } from './components/userhome/userhome.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { DualityhomeComponent } from './components/dualityhome/dualityhome.component';
import { CheckInComponent } from './components/check-in/check-in.component';
import { DatePipe } from '@angular/common';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';

@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    RegisterComponent,
    NotFoundComponent,
    UserhomeComponent,
    LoginComponent,
    LogoutComponent,
    DualityhomeComponent,
    CheckInComponent,


  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    CalendarModule.forRoot({ provide: DateAdapter, useFactory: adapterFactory }),



  ],
  providers: [DatePipe],
  bootstrap: [AppComponent],

})
export class AppModule { }
