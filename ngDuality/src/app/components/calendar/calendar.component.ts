import { Component } from '@angular/core';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.css']
})
export class CalendarComponent {

  currentDate: Date = new Date();
  thisDayAndDate: string = this.currentDate.getDay +" "+ this.currentDate.getDate
  thisMonthAndYear: string = this.currentDate.getMonth +" "+ this.currentDate.getFullYear

  constructor(private date:DatePipe){


  }
}
