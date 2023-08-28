export class Calendar {
  firstWeek: {
    lastMonth: number [];
    thisMonth: number [];
  };

  secondWeek: number [];

  thirdWeek: number [];

  constructor (
    firstWeek = {lastMonth: [], thisMonth: []}, secondWeek = [], thirdWeek = []
  ) {
    this.firstWeek = firstWeek;
    this.secondWeek = secondWeek;
    this.thirdWeek = thirdWeek;
  }

}
