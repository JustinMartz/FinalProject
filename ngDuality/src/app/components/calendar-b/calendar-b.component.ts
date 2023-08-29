import {
  Component,
  EventEmitter,
  OnInit,
  Output,
  ViewChild,
} from '@angular/core';
import { Calendar } from 'src/app/models/calendar';
import { CalendarService } from 'src/app/services/calendar.service';
import { CheckInComponent } from '../check-in/check-in.component';

@Component({
  selector: 'app-calendar-b',
  templateUrl: './calendar-b.component.html',
  styleUrls: ['./calendar-b.component.css'],
})
export class CalendarBComponent implements OnInit {
  // @Output() submitClicked: EventEmitter<void> = new EventEmitter();
  @ViewChild(CheckInComponent, { static: true }) checkIn!: CheckInComponent;

  currentDate: Date | undefined;
  displayDate: Date | undefined;

  displayMonth: Calendar = new Calendar();

  formView: boolean = false;

  currentDay: string = '';

  constructor(private calendarService: CalendarService) {}

  ngOnInit(): void {
    this.currentDate = new Date();
    this.displayMonth = this.calendarService.getMonth();
    this.currentDay = ('0' + this.currentDate.getDate()).slice(-2);

    console.log('*** ngOnInit()');
    console.log('today: ' + this.currentDay);
    console.log(this.displayMonth);
    console.log(this.displayMonth.firstWeek);
    console.log(this.displayMonth.firstWeek.lastMonth);
  }

  dayClick(day: number) {
    console.log('day ' + day + ' gettin clicked');
    this.displayDate = new Date();
    this.displayDate?.setDate(day);
    console.log('displayDate: ' + this.displayDate);

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
      return 'backface-visibility: visible;';
    }

    return '';
  }

  showFront() {
    if (this.formView) {
      return 'display: none;';
    }

    return '';
  }

  closeForm() {
    this.formView = false;
  }

  isToday(day: number) {
    if (day === parseInt(this.currentDay)) {
      return 'active';
    }

    return '';
  }

  checkinButton() {
    console.log('parent button clicked');
    this.checkIn.submitDailyBR();
  }
}
