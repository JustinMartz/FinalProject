export class Calendar {
  firstWeek: {
    lastMonth: number [];
    thisMonth: number [];
  };

  secondWeek: number [];
  thirdWeek: number [];
  fourthWeek: number [];

  fifthWeek: {
    thisMonth: number[];
    nextMonth: number[];
  };

  sixthWeek: {
    thisMonth: number[];
    nextMonth: number[];
  };

  constructor (
    firstWeek = {lastMonth: [], thisMonth: []},
    secondWeek = [],
    thirdWeek = [],
    fourthWeek = [],
    fifthWeek = {thisMonth: [], nextMonth: []},
    sixthWeek = {thisMonth: [], nextMonth: []}
  ) {
    this.firstWeek = firstWeek;
    this.secondWeek = secondWeek;
    this.thirdWeek = thirdWeek;
    this.fourthWeek = fourthWeek;
    this.fifthWeek = fifthWeek;
    this.sixthWeek = fifthWeek;
  }

}
