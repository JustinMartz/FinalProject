import {
  Component,
  OnInit,
  QueryList,
  ViewChildren,
} from '@angular/core';
import { Calendar } from 'src/app/models/calendar';
import { CalendarService } from 'src/app/services/calendar.service';
import { CheckInComponent } from '../check-in/check-in.component';
import { BehaviorReportService } from 'src/app/services/behavior-report.service';
import { BehaviorReport } from 'src/app/models/behavior-report';

@Component({
  selector: 'app-calendar-b',
  templateUrl: './calendar-b.component.html',
  styleUrls: ['./calendar-b.component.css'],
})
export class CalendarBComponent implements OnInit {
  // @Output() submitClicked: EventEmitter<void> = new EventEmitter();
  // @ViewChild(CheckInComponent, { static: true }) checkIn: CheckInComponent = {} as CheckInComponent;
  // @ViewChild('fred', { static: true }) checkIn: CheckInComponent = {} as CheckInComponent;
  @ViewChildren(CheckInComponent) children!: QueryList<CheckInComponent>;

  currentDate: Date | undefined;
  displayDate: Date | undefined;

  displayMonth: Calendar = new Calendar();

  formView: boolean = false;
  dayClicked: boolean = false;

  currentDay: string = '';

  daysWithBRs: boolean[] = [];
  displayDateBRs: BehaviorReport[] = [];
  monthBRs: BehaviorReport[] = [];

  checkInOrPrevReport: boolean = false;

  constructor(private calendarService: CalendarService, private brService: BehaviorReportService) {}

  ngOnInit(): void {
    this.currentDate = new Date();
    this.displayDate = new Date();
    this.displayMonth = this.calendarService.getMonth();
    this.currentDay = ('0' + this.currentDate.getDate()).slice(-2);
    this.getBRsForMonth(this.displayDate.toISOString());
    this.brService.getReportsForMonth(this.displayDate.toISOString()).subscribe({
      next: (reports) => {
        console.log('*** month reports');
        console.log(reports);
        this.monthBRs = reports;
      },
      error: (msg) => {
        console.error('Error in CalendarBComponent.ngOnInit()');
        console.error(msg);
      }
    });
    console.log('in ngOnInit()');
    console.log(this.children);
  }

  ngAfterViewInit() {
    console.log('in ngAfterViewInit()');
    console.log(this.children);
  }

  dayClick(day: number) {
    this.dayClicked = true;
    this.displayDateBRs = [];

    this.displayDate = new Date();
    this.displayDate?.setUTCDate(day);

    this.brService.getBRsForDay(this.displayDate.toISOString()).subscribe({
      next: (reports) => {

        this.displayDateBRs = [];
        this.displayDateBRs = reports;
      },
      error: (fail) => {
        console.error('Error in CalendarBComponent.dayClick()');
        console.error(fail);
      }
    });
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
    this.dayClicked = false;
  }

  isToday(day: number) {
    if (day === parseInt(this.currentDay)) {
      return 'active';
    }

    if (this.daysWithBRs[day - 1]) {
      return this.calcStatus(day);
    }

    return '';
  }

  checkinButton() {
    console.log('checkinButton()');
    let checkIn = this.children.get(0);
    console.log(checkIn);
    console.log(this.dayClicked);
    checkIn?.submitDailyBR();
  }

  getBRsForMonth(isodate: string) {
    this.brService.getBRsForMonth(isodate).subscribe({
      next: (list) => {
        console.log(list);
        this.daysWithBRs = list;
        console.log(this.daysWithBRs);
      },
      error: (bigError) => {
        console.error('Error in CalendarComponent.getBRsForMonth()');
        console.error(bigError);
      }
    });
  }

  calcStatus(day: number) {
    let total: number = 0;
    let avg: number = 0;
    let calculableBehaviorReportsForDay: BehaviorReport[] = [];
    for (let br of this.monthBRs) {
      let myArray = br.createDate.split("-");
      let secondArray = myArray[2].split('T');
      if (secondArray[0] == day.toString()) {
        console.log(secondArray[0] + ' and ' + day.toString() + ' match');
        calculableBehaviorReportsForDay.push(br);
      }
      console.log(secondArray[0]);
    }
    for (let b of calculableBehaviorReportsForDay) {
      total += b.intensity;
    }
    avg = total / calculableBehaviorReportsForDay.length;
    console.log('average of all intensity values: ' + avg);
    if (avg > 0 && avg < 4) {
      return 'event-good';
    }
    if (avg >= 4  && avg < 7) {
      return 'event-okay';
    }
    if (avg >= 7  && avg <= 10) {
      return 'event-bad';
    }

    return 'event';
  }

  goBackOneMonth() {
    console.log('*** goBackOneMonth()');
    console.log('month before: ' + this.displayDate?.getUTCMonth)
    this.displayDate?.setUTCMonth(this.displayDate.getUTCMonth() - 1);
  }
}
