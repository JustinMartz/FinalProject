import { Behavior } from './behavior';
import { User } from "./user";

export class BehaviorReport {
  id: number;
  user: User;
  behavior: Behavior;
  intensity: number;
  createDate: string;


  constructor(
    id: number = 0,
    user: User = new User(),
    behavior: Behavior = new Behavior(),
    intensity: number = 0,
    createDate:string=''

  ) {
    this.id = id;
    this.user = user;
    this.behavior=behavior;
    this.intensity=intensity;
    this.createDate=createDate;

  }

}
