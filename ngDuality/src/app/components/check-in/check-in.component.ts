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

  // 1. if checkbox is checked, intensity slider appears
  // 2. new BehaviorReport object is made
  // 3. object gets intensity and name of behavior added to it

  ngOnInit(): void {
    this.behaviorService.index().subscribe({
      next: (behaviorList) => {
        this.behaviors = behaviorList;
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
    console.log('*** intensity: ' + intensity);
    console.log('behavior: ' + behavior);
    console.log('behaviorType: ' + behavior.behaviorType);
    this.intensityByBehaviorId[behavior.id] = intensity; //
    let updatedBR: BehaviorReport = new BehaviorReport();
    updatedBR.behavior = behavior;
    updatedBR.intensity = intensity;
    updatedBR.user = this.loggedInUser;
    console.log(updatedBR);

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
