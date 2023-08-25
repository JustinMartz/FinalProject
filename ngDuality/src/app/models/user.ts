import { first } from "rxjs";
import { BehaviorReport } from "./behavior-report";
import { BehaviorReportRemark } from "./behavior-report-remark";
import { Resource } from "./resource";

export class User {
  id: number;
  username: string;
  password: string;
  email: string;
  role: string;
  enabled: boolean;
  avatar: string;
  firstName: string;
  lastName: string;
  createDate: string;
  udpateDate: string;
  dob: string;
  aboutMe: string;
  behaviorReports: BehaviorReport[] | undefined;
  behaviorReportRemarks: BehaviorReportRemark[] | undefined;
  resources: Resource [] | undefined;
  flaggedPosts: Post [] | undefined;
  posts: Post [] | undefined;


  constructor(
    id: number = 0,
    username: string = '',
    password: string = '',
    email: string = '',
    role: string = '',
    enabled: boolean = false,
    avatar: string = '',
    firstName: string = '',
    lastName: string = '',
    createDate: string = '',
    updateDate: string = '',
    dob: string = '',
    aboutMe: string = '',
    behaviorReports: BehaviorReport [] = [],
    behaviorReportRemarks: BehaviorReportRemark [] = [],
    resources: Resource [] = [],
    flaggedPosts: Post[] | undefined,
    posts: Post[] | undefined




  ) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.email = email;
    this.role = role;
    this.enabled = enabled;
    this.avatar = avatar;
    this.firstName = firstName;
    this.lastName = lastName;
    this.createDate = createDate;
    this.udpateDate = updateDate;
    this.dob = dob;
    this.aboutMe = aboutMe;
    this.behaviorReports = behaviorReports;
    this.behaviorReportRemarks = behaviorReportRemarks;
    this.resources = resources;
    this.flaggedPosts = flaggedPosts;
    this.posts = posts;
  }

}
