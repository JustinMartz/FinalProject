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


// const app = {
//   settings: {
//     container: $('.calendar'),
//     calendar: $('.front'),
//     days: $('.weeks span'),
//     form: $('.back'),
//     input: $('.back input'),
//     buttons: $('.back button')
//   },

//   init: function(): void {
//     const instance = this;
//     const settings = this.settings;
//     this.bindUIActions();
//   },

//   swap: function(currentSide: JQuery, desiredSide: JQuery): void {
//     const settings = this.settings;
//     settings.container.toggleClass('flip');

//     currentSide.fadeOut(900);
//     currentSide.hide();
//     desiredSide.show();

//   },

//   bindUIActions: function(): void {
//     const instance = this;
//     const settings = this.settings;
//     settings.days.on('click', function(){
//       instance.swap(settings.calendar, settings.form);
//       settings.input.focus();
//     });

//     settings.buttons.on('click', function(){
//       instance.swap(settings.form, settings.calendar);
//     });
//   }
// }

// app.init();
