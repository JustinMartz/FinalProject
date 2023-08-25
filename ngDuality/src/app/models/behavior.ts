import { BehaviorType } from "./behavior-type";

export class Behavior {
  id: number;
  name: string;
  severity: number;
  behaviorType: BehaviorType;


  constructor(
    id: number = 0,
    name: string = '',
    severity: number=0,
    behaviorType: BehaviorType = new BehaviorType()

  ) {
    this.id = id;
    this.name = name;
    this.severity=severity;
    this.behaviorType=behaviorType;

  }

}
