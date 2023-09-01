import { Component, OnInit, QueryList, ViewChildren } from '@angular/core';
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
  displayMonthText: string = '';

  daysWithBRs: boolean[] = [];
  displayDateBRs: BehaviorReport[] = [];
  monthBRs: BehaviorReport[] = [];

  correctISOString: string = '';
  displayDateString: string = '';

  checkInOrPrevReport: boolean = false;

  constructor(
    private calendarService: CalendarService,
    private brService: BehaviorReportService
  ) {}

  ngOnInit(): void {
    this.currentDate = new Date();
    console.log('*** currentDate day:  ' + this.currentDate.getDate());
    console.log('*** currentDate month:  ' + this.currentDate.getUTCMonth());


    this.displayDate = new Date();
    this.displayMonth = this.calendarService.getMonth();
    this.currentDay = ('0' + this.currentDate.getDate()).slice(-2);

    this.correctISOString = this.buildMonthCorrectISOString(this.currentDate.getDate());

    console.log('correctISOString: ' + this.correctISOString);
    console.log('bigISOString: ' + this.correctISOString);
    // this.getBRsForMonth(this.displayDate.toISOString());
    this.getBRsForMonth(this.correctISOString);
    this.brService
      .getReportsForMonth(this.correctISOString)
      .subscribe({
        next: (reports) => {
          console.log('*** month reports');
          console.log(reports);
          this.monthBRs = reports;
        },
        error: (msg) => {
          console.error('Error in CalendarBComponent.ngOnInit()');
          console.error(msg);
        },
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

    console.log('**** dayClick(): displayDate: ' + this.displayDate)
    this.displayDate = new Date(this.displayDate!.setDate(day));
    this.displayDateString = this.displayDate.toLocaleDateString('en-US');
    console.log('**** after setting day: ' + this.displayDate);
    console.log('*** dayClick(): day: ' + day);

    this.brService.getBRsForDay(this.buildDayCorrectISOString(day)).subscribe({
      next: (reports) => {
        this.displayDateBRs = [];
        this.displayDateBRs = reports;
      },
      error: (fail) => {
        console.error('Error in CalendarBComponent.dayClick()');
        console.error(fail);
      },
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
    if (this.displayDate?.getMonth() === this.currentDate?.getMonth()) {
      if (day === this.currentDate?.getDate()) {
        return 'active';
      }
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
      },
    });
  }

  calcStatus(day: number) {
    let total: number = 0;
    let avg: number = 0;
    let calculableBehaviorReportsForDay: BehaviorReport[] = [];


    for (let br of this.monthBRs) {
      console.log('br id: ' + br.id);
      let myArray = br.createDate.split('-');
      let secondArray = myArray[2].split('T');
      if (secondArray[0] == day.toString()) {
        calculableBehaviorReportsForDay.push(br);
      }
    }
    for (let b of calculableBehaviorReportsForDay) {
      total += b.intensity;
    }
    avg = total / calculableBehaviorReportsForDay.length;

    if (avg > 0 && avg < 4) {
      return 'event-good';
    }
    if (avg >= 4 && avg < 7) {
      return 'event-okay';
    }
    if (avg >= 7 && avg <= 10) {
      return 'event-bad';
    }

    return 'event';
  }

  goBackOneMonth() {
    console.log('*** goBackOneMonth()');
    if (this.displayDate) {
      console.log('date before: ' + this.displayDate?.toISOString());
      let newDate = new Date(this.displayDate);
      newDate.setUTCMonth(this.displayDate.getUTCMonth() - 1);
      this.displayDate = newDate;
      console.log('displayDate after: ' + this.displayDate?.toISOString());
      console.log('Current display month: ');
      console.log(this.displayMonth);
      // this.displayMonth = new Calendar();
      console.log('displayDate' + this.displayDate?.toISOString());
      this.displayMonth = this.calendarService.getPreviousMonth(
        this.displayDate!
      );
      console.log('New display month: ');
      console.log(this.displayMonth);
      this.getBRsForMonth(this.displayDate.toISOString());

      // let dayToCalcString = this.buildDayCorrectISOString(day);
      this.brService
      .getReportsForMonth(this.displayDate.toISOString())
      .subscribe({
        next: (reports) => {
          console.log('*** month reports');
          console.log(reports);
          this.monthBRs = reports;
        },
        error: (msg) => {
          console.error('Error in CalendarBComponent.ngOnInit()');
          console.error(msg);
        },
      });
    }
  }

  goForwardOneMonth() {
    console.log('*** goForwardOneMonth()');
    if (this.displayDate) {
      console.log('date before: ' + this.displayDate?.toISOString());
      let newDate = new Date(this.displayDate);
      newDate.setUTCMonth(this.displayDate.getUTCMonth() + 1);
      this.displayDate = newDate;
      console.log('displayDate after: ' + this.displayDate?.toISOString());
      console.log('Current display month: ');
      console.log(this.displayMonth);
      // this.displayMonth = new Calendar();
      console.log('displayDate' + this.displayDate?.toISOString());
      this.displayMonth = this.calendarService.getNextMonth(
        this.displayDate!
      );
      console.log('New display month: ');
      console.log(this.displayMonth);
      this.getBRsForMonth(this.displayDate.toISOString());
      // change displayMonthText
      // change displayYearText
    }
  }

  forwardIsPossible() {
    if (this.displayDate?.getMonth() === this.currentDate?.getMonth()) {
      return false;
    } else {
      return true;
    }
  }

  buildMonthCorrectISOString(day: number) {
    console.log('buildMonthCorrectISOString() **** day: ' + day);
    console.log('currentDate month: ' + this.currentDate?.getMonth());
    let month = '';
    switch (this.currentDate?.getMonth()) {
      case 6:
        month = '07';
        break;
      case 7:
        month = '08';
        break;
      case 8:
        month = '09';
        break;
      case 9:
        month = '10';
        break;
    }

    let myArray = this.currentDate?.toISOString().split("-");
    let isodate = '';

    if (myArray) {
      isodate = myArray[0] + '-' + month + '-' + myArray[2];
    }
    console.log('returned isodate: ' + isodate);

    return isodate;
  }

  buildDayCorrectISOString(passedInDay: number) {
    let day = passedInDay.toLocaleString('en-US', {
      minimumIntegerDigits: 2});

      console.log('the day ' + day);

    let myArray = this.displayDate?.toISOString().split("-");
    let otherArray = [];
    let isodate = '';

    if (myArray) {
      isodate = myArray[0] + '-' + myArray[1] + '-' + day + 'T00:00:00.000';
    }
    console.log('new isodate: ' + isodate);

    return isodate;
  }
}


