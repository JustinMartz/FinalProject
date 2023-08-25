import { User } from "./user";

export class Resource {
  id: number;
  resourceLink: string;
  description: string;
  createDate: string;
  active: boolean;
  creator: User;




  constructor(
    id: number = 0,
    resourceLink: string = '',
    description: string = '',
    createDate: string = '',
    active: boolean = false,
    creator: User = new User()





  ) {
    this.id = id;
    this.resourceLink = resourceLink;
    this.description = description;
    this.createDate = createDate;
    this.active = active;
    this.creator = creator;



  }

}
