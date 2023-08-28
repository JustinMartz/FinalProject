import { Injectable } from '@angular/core';
import { Calendar } from '../models/calendar';

@Injectable({
  providedIn: 'root'
})
export class CalendarService {
  currentDate: Date | undefined;
  constructor() { }

  // pass in monthToDisplay
  // if monthToDisplay's last month is 31 days, return first| week appropriately
  // displayMonth = {
  // firstWeek: { [28, 29, 30, 31], [1, 2, 3]},
  // secondWeek: [ 4, 5, 6, 7, 8, 9, 10],
  // }

  getMonth() {
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
          lastMonth: [28, 29, 30, 31],
          thisMonth: [1, 2, 3]
         },
         secondWeek: [4, 5, 6, 7, 8, 9, 10],
         thirdWeek: [11, 12, 13, 14, 15, 16, 17]
        };
        break;
      case 'February':
        cal = {
          firstWeek: {
           lastMonth: [22, 23, 24, 25, 26, 27, 28],
           thisMonth: []
          },
          secondWeek: [1, 2, 3, 4, 5, 6, 7],
          thirdWeek: [8, 9, 10, 11, 12, 13, 14]
         };
        break;
      case 'April':
      case 'June':
      case 'September':
      case 'November':
        cal = {
          firstWeek: {
           lastMonth: [27, 28, 29, 30, 30],
           thisMonth: [1, 2]
          },
          secondWeek: [3, 4, 5, 6, 7, 8, 9],
          thirdWeek: [10, 11, 12, 13, 14, 15, 16]
         };
        break;
    }

    return cal;
  }
}

