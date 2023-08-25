import { User } from "./user";

export class BehaviorReportRemark {
  id: number;
  remarks: string;
  createDate: string;
  user: User;


  constructor(
    id: number = 0,
    remarks: string = '',
    createDate: string = '',
    user: User = new User()

  ) {
    this.id = id;
    this.remarks = remarks;
    this.createDate=createDate;
    this.user=user;

  }
}
