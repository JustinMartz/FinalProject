import { Component, OnInit } from '@angular/core';
import { Calendar } from 'src/app/models/calendar';
import { CalendarService } from 'src/app/services/calendar.service';

@Component({
  selector: 'app-calendar-b',
  templateUrl: './calendar-b.component.html',
  styleUrls: ['./calendar-b.component.css']
})
export class CalendarBComponent implements OnInit {
  currentDate: Date | undefined;
  displayDate: Date | undefined;

  displayMonth: Calendar = new Calendar();

  formView: boolean = false;

  currentDay: number = 0;

  constructor (private calendarService: CalendarService) {}

  ngOnInit(): void {
    this.currentDate = new Date();
    this.displayMonth = this.calendarService.getMonth();
    this.currentDay = this.currentDate.getDay();
    console.log('*** ngOnInit()');
    console.log(this.displayMonth);
    console.log(this.displayMonth.firstWeek);
    console.log(this.displayMonth.firstWeek.lastMonth);
  }

  dayClick(day: number) {
    console.log('day ' + day + ' gettin clicked');
    this.formView = true;
  }

  flip() {
    if (this.formView) {
      return 'container flip';
    }

    return 'container';
  }

  showForm() {
    if (this.formView) {
      return 'backface-visibility: visible;'
    }

    return '';
  }

  showFront() {
    if (this.formView) {
      return 'display: none;'
    }

    return '';
  }

  closeForm() {
    this.formView = false;
  }
}
