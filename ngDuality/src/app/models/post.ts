import { User } from "./user";

export class Post {
  id: number;
  title: string;
  creator: User;
  active: boolean;
  createDate: string;
  message: string;
  anonymous: boolean;
  comments: Comment[] | undefined;
  personal: boolean;
  userWhoFlagged: User[] | undefined;


  constructor(
    id: number = 0,
    title: string = '',
    creator: User = new User(),
    active: boolean = true,
    createDate: string = '',
    message: string = '',
    anonymous: boolean = false,
    personal: boolean = false,
    comments: Comment[] = [],
    usersWhoFlagged: User[] = []
) {
    this.id = id;
    this.title = title;
    this.creator = creator;
    this.active = active;
    this.createDate = createDate;
    this.message = message;
    this.anonymous = anonymous;
    this.personal = personal;
    this.comments = comments;
    this.userWhoFlagged = usersWhoFlagged;
}

}
