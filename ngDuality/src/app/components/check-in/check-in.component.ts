import { BehaviorService } from './../../services/behavior.service';
import { Component, OnInit } from '@angular/core';
import { Behavior } from 'src/app/models/behavior';
import { BehaviorReport } from 'src/app/models/behavior-report';
import { AuthService } from 'src/app/services/auth.service';


@Component({
  selector: 'app-check-in',
  templateUrl: './check-in.component.html',
  styleUrls: ['./check-in.component.css']
})
export class CheckInComponent implements OnInit{

  constructor(private behaviorService: BehaviorService, private authService: AuthService){

  }
  behaviors: Behavior[] =[];
  newBehaviorReports: BehaviorReport[] = [];
  newBR: BehaviorReport = new BehaviorReport();

  // 1. if checkbox is checked, intensity slider appears
  // 2. new BehaviorReport object is made
  // 3. object gets intensity and name of behavior added to it


  ngOnInit(): void {
    this.behaviorService.index().subscribe(
      {
        next: (behaviorList) => {
          this.behaviors = behaviorList;
        },
        error: (somethingBad) => {
          console.error('BehaviorListComponent.reload: error loading behaviors');
          console.error(somethingBad);
        }
      }

    );
}

  updateBR(intensity: number, behavior: Behavior) {
    console.log('*** intensity: ' + intensity);
    let updatedBR: BehaviorReport = new BehaviorReport();
    updatedBR.behavior = behavior;
    updatedBR.intensity = intensity;
    this.authService.getLoggedInUser().subscribe({
      next: (user) => {
        updatedBR.user = user;
      },
      error: (fail) => {
        console.error('CheckInComponent.updateBR(): Error getting user');
        console.error(fail);
      }
    });
    console.log(updatedBR);
  }


}
