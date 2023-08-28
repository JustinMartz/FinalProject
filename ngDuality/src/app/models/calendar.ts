export class Calendar {
  firstWeek: {
    lastMonth: number [];
    thisMonth: number [];
  };

  secondWeek: number [];
  thirdWeek: number [];
  fourthWeek: number [];
  fifthWeek: number [];

  constructor (
    firstWeek = {lastMonth: [], thisMonth: []}, secondWeek = [], thirdWeek = [], fourthWeek = [], fifthWeek = []
  ) {
    this.firstWeek = firstWeek;
    this.secondWeek = secondWeek;
    this.thirdWeek = thirdWeek;
    this.fourthWeek = fourthWeek;
    this.fifthWeek = fifthWeek;
  }

}
