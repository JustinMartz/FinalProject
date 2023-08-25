import { BehaviorService } from './../../services/behavior.service';
import { Component, OnInit } from '@angular/core';
import { Behavior } from 'src/app/models/behavior';

@Component({
  selector: 'app-check-in',
  templateUrl: './check-in.component.html',
  styleUrls: ['./check-in.component.css']
})
export class CheckInComponent implements OnInit{

  constructor(private behaviorService: BehaviorService){

  }
  behaviors: Behavior[] =[];



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



}
