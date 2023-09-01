import { Injectable } from '@angular/core';
import { Calendar } from '../models/calendar';

@Injectable({
  providedIn: 'root',
})
export class CalendarService {
  currentDate: Date | undefined;
  constructor() {}

  getMonth() {
    console.log('*** getMonth()');
    this.currentDate = new Date();
    let cal = new Calendar();
    switch (this.currentDate.toLocaleString('default', { month: 'long' })) {
      case 'January':
      case 'March':
      case 'May':
      case 'July':
      case 'August':
      case 'October':
      case 'December':
        cal = {
          firstWeek: {
            lastMonth: [30],
            thisMonth: [1, 2, 3, 4, 5, 6],
          },
          secondWeek: [7, 8, 9, 10, 11, 12, 13],
          thirdWeek: [14, 15, 16, 17, 18, 19, 20],
          fourthWeek: [21, 22, 23, 24, 25, 26, 27],
          fifthWeek: {
            thisMonth: [28, 29, 30, 31],
            nextMonth: [1, 2, 3],
          },
          sixthWeek: { thisMonth: [], nextMonth: [] },
        };
        break;
      case 'February':
        cal = {
          firstWeek: {
            lastMonth: [25, 26, 27, 28, 29, 30, 31],
            thisMonth: [],
          },
          secondWeek: [1, 2, 3, 4, 5, 6, 7],
          thirdWeek: [8, 9, 10, 11, 12, 13, 14],
          fourthWeek: [15, 16, 17, 18, 19, 20, 21],
          fifthWeek: {
            thisMonth: [28, 29, 30, 31],
            nextMonth: [1, 2, 3],
          },
          sixthWeek: { thisMonth: [], nextMonth: [] },
        };
        break;
      case 'April':
      case 'June':
      case 'September':
        cal = {
          firstWeek: {
            lastMonth: [28, 29, 30, 31],
            thisMonth: [1, 2, 3],
          },
          secondWeek: [4, 5, 6, 7, 8, 9, 10],
          thirdWeek: [11, 12, 13, 14, 15, 16, 17],
          fourthWeek: [18, 19, 20, 21, 22, 23, 24],
          fifthWeek: {
            thisMonth: [25, 26, 27, 28, 29, 30],
            nextMonth: [1],
          },
          sixthWeek: { thisMonth: [], nextMonth: [] },
        };
        break;
      case 'November':
        cal = {
          firstWeek: {
            lastMonth: [27, 28, 29, 30, 30],
            thisMonth: [1, 2],
          },
          secondWeek: [3, 4, 5, 6, 7, 8, 9],
          thirdWeek: [10, 11, 12, 13, 14, 15, 16],
          fourthWeek: [17, 18, 19, 20, 21, 22, 23],
          fifthWeek: {
            thisMonth: [28, 29, 30, 31],
            nextMonth: [1, 2, 3],
          },
          sixthWeek: { thisMonth: [], nextMonth: [] },
        };
        break;
    }

    return cal;
  }

  getPreviousMonth(displayDate: Date) {
    console.log('getPreviousMonth()');
    console.log('displayDate coming into getPreviousMonth()');
    let tempDate = new Date(displayDate);
    let cal = new Calendar();
    console.log(
      'previous month: ' + tempDate.toLocaleString('default', { month: 'long' })
    );
    switch (tempDate.toLocaleString('default', { month: 'long' })) {
      case 'January':
      case 'March':
      case 'May':
      case 'July':
        cal = {
          firstWeek: {
            lastMonth: [26, 27, 28, 29, 30],
            thisMonth: [1, 2],
          },
          secondWeek: [3, 4, 5, 6, 7, 8, 9],
          thirdWeek: [10, 11, 12, 13, 14, 15, 16],
          fourthWeek: [17, 18, 19, 20, 21, 22, 23],
          fifthWeek: {
            thisMonth: [24, 25, 26, 27, 28, 29, 30],
            nextMonth: [],
          },
          sixthWeek: {
            thisMonth: [31],
            nextMonth: [1, 2, 3, 4, 5, 6],
          },
        };
        break;
      case 'August':
      case 'October':
      case 'December':
        cal = {
          firstWeek: {
            lastMonth: [28, 29, 30, 31],
            thisMonth: [1, 2, 3],
          },
          secondWeek: [4, 5, 6, 7, 8, 9, 10],
          thirdWeek: [11, 12, 13, 14, 15, 16, 17],
          fourthWeek: [18, 19, 20, 21, 22, 23, 24],
          fifthWeek: {
            thisMonth: [28, 29, 30, 31],
            nextMonth: [1, 2, 3],
          },
          sixthWeek: { thisMonth: [], nextMonth: [] },
        };
        break;
      case 'February':
        cal = {
          firstWeek: {
            lastMonth: [25, 26, 27, 28, 29, 30, 31],
            thisMonth: [],
          },
          secondWeek: [1, 2, 3, 4, 5, 6, 7],
          thirdWeek: [8, 9, 10, 11, 12, 13, 14],
          fourthWeek: [15, 16, 17, 18, 19, 20, 21],
          fifthWeek: {
            thisMonth: [28, 29, 30, 31],
            nextMonth: [1, 2, 3],
          },
          sixthWeek: { thisMonth: [], nextMonth: [] },
        };
        break;
      case 'April':
      case 'June':
      case 'September':
      case 'November':
        cal = {
          firstWeek: {
            lastMonth: [27, 28, 29, 30, 30],
            thisMonth: [1, 2],
          },
          secondWeek: [3, 4, 5, 6, 7, 8, 9],
          thirdWeek: [10, 11, 12, 13, 14, 15, 16],
          fourthWeek: [17, 18, 19, 20, 21, 22, 23],
          fifthWeek: {
            thisMonth: [28, 29, 30, 31],
            nextMonth: [1, 2, 3],
          },
          sixthWeek: { thisMonth: [], nextMonth: [] },
        };
        break;
    }
    console.log('Returning new month: ' + cal);
    return cal;
  }

  getNextMonth(displayDate: Date) {
    console.log('getPreviousMonth()');
    console.log('displayDate coming into getPreviousMonth()');
    let tempDate = new Date(displayDate);
    let cal = new Calendar();
    console.log(
      'previous month: ' + tempDate.toLocaleString('default', { month: 'long' })
    );
    switch (tempDate.toLocaleString('default', { month: 'long' })) {
      case 'January':
      case 'March':
      case 'May':
      case 'July':
        cal = {
          firstWeek: {
            lastMonth: [26, 27, 28, 29, 30],
            thisMonth: [1, 2],
          },
          secondWeek: [3, 4, 5, 6, 7, 8, 9],
          thirdWeek: [10, 11, 12, 13, 14, 15, 16],
          fourthWeek: [17, 18, 19, 20, 21, 22, 23],
          fifthWeek: {
            thisMonth: [24, 25, 26, 27, 28, 29, 30],
            nextMonth: [],
          },
          sixthWeek: {
            thisMonth: [31],
            nextMonth: [1, 2, 3, 4, 5, 6],
          },
        };
        break;
      case 'August':
        cal = {
          firstWeek: {
            lastMonth: [28, 29, 30, 31],
            thisMonth: [1, 2, 3],
          },
          secondWeek: [4, 5, 6, 7, 8, 9, 10],
          thirdWeek: [11, 12, 13, 14, 15, 16, 17],
          fourthWeek: [18, 19, 20, 21, 22, 23, 24],
          fifthWeek: {
            thisMonth: [28, 29, 30, 31],
            nextMonth: [1, 2, 3],
          },
          sixthWeek: { thisMonth: [], nextMonth: [] },
        };
        break;
      case 'October':
      case 'December':
        cal = {
          firstWeek: {
            lastMonth: [28, 29, 30, 31],
            thisMonth: [1, 2, 3],
          },
          secondWeek: [4, 5, 6, 7, 8, 9, 10],
          thirdWeek: [11, 12, 13, 14, 15, 16, 17],
          fourthWeek: [18, 19, 20, 21, 22, 23, 24],
          fifthWeek: {
            thisMonth: [28, 29, 30, 31],
            nextMonth: [1, 2, 3],
          },
          sixthWeek: { thisMonth: [], nextMonth: [] },
        };
        break;
      case 'February':
        cal = {
          firstWeek: {
            lastMonth: [25, 26, 27, 28, 29, 30, 31],
            thisMonth: [],
          },
          secondWeek: [1, 2, 3, 4, 5, 6, 7],
          thirdWeek: [8, 9, 10, 11, 12, 13, 14],
          fourthWeek: [15, 16, 17, 18, 19, 20, 21],
          fifthWeek: {
            thisMonth: [28, 29, 30, 31],
            nextMonth: [1, 2, 3],
          },
          sixthWeek: { thisMonth: [], nextMonth: [] },
        };
        break;
      case 'April':
      case 'June':
      case 'September':
      case 'November':
        cal = {
          firstWeek: {
            lastMonth: [27, 28, 29, 30, 30],
            thisMonth: [1, 2],
          },
          secondWeek: [3, 4, 5, 6, 7, 8, 9],
          thirdWeek: [10, 11, 12, 13, 14, 15, 16],
          fourthWeek: [17, 18, 19, 20, 21, 22, 23],
          fifthWeek: {
            thisMonth: [28, 29, 30, 31],
            nextMonth: [1, 2, 3],
          },
          sixthWeek: { thisMonth: [], nextMonth: [] },
        };
        break;
    }
    console.log('Returning new month: ' + cal);
    return cal;
  }
}
