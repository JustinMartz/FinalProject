import { BehaviorService } from './../../services/behavior.service';
import { Component, EventEmitter, Input, OnInit } from '@angular/core';
import { Behavior } from 'src/app/models/behavior';
import { BehaviorReport } from 'src/app/models/behavior-report';
import { BehaviorReportRemark } from 'src/app/models/behavior-report-remark';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { BehaviorReportRemarkService } from 'src/app/services/behavior-report-remark-service.service';
import { BehaviorReportService } from 'src/app/services/behavior-report.service';

@Component({
  selector: 'app-check-in',
  templateUrl: './check-in.component.html',
  styleUrls: ['./check-in.component.css'],
})
export class CheckInComponent implements OnInit {
  @Input() submitClicked: EventEmitter<void> = new EventEmitter();
  @Input() checkInDate: Date | undefined;
  @Input() prevBehaviorReports: BehaviorReport[] = [];

  constructor(
    private behaviorService: BehaviorService,
    private authService: AuthService,
    private behaviorReportService: BehaviorReportService,
    private behaviorReportRemarkService: BehaviorReportRemarkService
  ) {}

  behaviors: Behavior[] = [];

  newBR: BehaviorReport = new BehaviorReport();

  intensityByBehaviorId: { [id: number]: number } = {}; //

  behaviorReports: BehaviorReport[] = [];

  behaviorReportRemark: BehaviorReportRemark = new BehaviorReportRemark();

  loggedInUser: User = new User();

  currentDate = new Date();

  isToday: boolean = false;

  ngOnInit(): void {
    console.log('checkInDate: ' + this.checkInDate?.toISOString());
    console.log('currentDate: ' + this.currentDate.toISOString());
    if (this.checkInDate != null && this.checkInDate.getUTCFullYear() == this.currentDate.getUTCFullYear()) {
      console.log('*** years match');
      if (this.checkInDate.getUTCMonth() == this.currentDate.getUTCMonth()) {
        console.log('*** months match');
        if (this.checkInDate.getUTCDate() == this.currentDate.getUTCDate()) {
          console.log('*** days match');
          this.isToday = true;
        }

      }
    }

    this.behaviorService.index().subscribe({
      next: (behaviorList) => {
        this.behaviors = behaviorList;
        console.log('*** behaviors.length: ' + this.behaviors.length);
        console.log('*** behaviors: ' + this.behaviors);
      },
      error: (somethingBad) => {
        console.error('BehaviorListComponent.reload: error loading behaviors');
        console.error(somethingBad);
      },
    });

    this.authService.getLoggedInUser().subscribe({
      next: (user) => {
        this.loggedInUser = user;
      },
      error: (fail) => {
        console.error('CheckInComponent.updateBR(): Error getting user');
        console.error(fail);
      },
    });
  }

  createBR(intensity: number, behavior: Behavior) {
    this.intensityByBehaviorId[behavior.id] = intensity; //
    let updatedBR: BehaviorReport = new BehaviorReport();
    updatedBR.behavior = behavior;
    updatedBR.intensity = intensity;
    updatedBR.user = this.loggedInUser;

    for (let br of this.behaviorReports) {
      if (br.behavior.id === behavior.id) {
        return;
      }
    }
    this.behaviorReports.push(updatedBR);
  }

  submitDailyBR() {
    console.log('child method called');
    console.log(this.behaviorReports);
    console.log(this.behaviorReportService);

    this.submitBehaviorReportRemark();

    for (let br of this.behaviorReports) {
      console.log(br);
      this.behaviorReportService.create(br).subscribe({
        next: (createdBehaviorReport) => {
          this.newBR = new BehaviorReport();
          this.behaviorReports = [];
        },
        error: (somethingBad) => {
          console.error(
            'submitDailtBRComponent.create(): error creating submitDailyBR'
          );
          console.error(somethingBad);
        },
      });
    }

  }

  submitBehaviorReportRemark() {
    this.behaviorReportRemark.user = this.loggedInUser
    this.behaviorReportRemarkService.create(this.behaviorReportRemark)
    .subscribe({
      next: (createdBehaviorReportRemark) => {
        this.behaviorReportRemark = new BehaviorReportRemark();
      },
      error: (somethingBad) => {
        console.error(
          'submitDailtBRComponent.reload: error loading submitDailyBR'
        );
        console.error(somethingBad);
      },
    });

  }
}
