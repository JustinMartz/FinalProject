import { User } from "./user";

export class DirectMessage {
  id: number;
  message: string;
  createDate: Date;
  active: boolean;
  sender: User;
  recipient: User;

  constructor(
      id: number = 0,
      message: string = "",
      createDate: Date = new Date(),
      active: boolean = true,
      sender: User = new User(),
      recipient: User = new User()
  ) {
      this.id = id;
      this.message = message;
      this.createDate = createDate;
      this.active = active;
      this.sender = sender;
      this.recipient = recipient;
  }
}

// Ensure that you also have a User model defined similarly in your Angular project.
