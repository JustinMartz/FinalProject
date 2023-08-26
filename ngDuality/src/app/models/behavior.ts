import { BehaviorType } from "./behavior-type";

export class Behavior {
  id: number;
  name: string;
  severity: number;
  behaviorType: BehaviorType;
  checked: boolean;

  constructor(
    id: number = 0,
    name: string = '',
    severity: number=0,
    behaviorType: BehaviorType = new BehaviorType(),
    checked: boolean = false

  ) {
    this.id = id;
    this.name = name;
    this.severity=severity;
    this.behaviorType=behaviorType;
    this.checked = checked;
  }

}
