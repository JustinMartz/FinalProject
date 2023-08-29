import { Component, OnInit } from '@angular/core';
import { Resource } from 'src/app/models/resource';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { ResourceService } from 'src/app/services/resource.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-userhome',
  templateUrl: './userhome.component.html',
  styleUrls: ['./userhome.component.css']
})
export class UserhomeComponent implements OnInit {

  loggedInUser: User = new User();

  userResources: Resource[] | undefined;


  constructor (
    private authService: AuthService,
    private resourceService: ResourceService) { }

  ngOnInit():void {
    if (this.authService.checkLogin()) {
      this.authService.getLoggedInUser().subscribe( {
        next: (user) => {
          this.loggedInUser = user;

          this.resourceService.getUserResources(this.loggedInUser.id).subscribe({
            next: (resources) => {
              this.userResources = resources;
            },
            error: (fail) => {
              console.error('ngOnInit(): Error getting resources');
              console.error(fail);
            }
          });
        },
        error: (fail) => {
          console.error('ngOnInit(): Error getting user');
          console.error(fail);
        }
      });


    }
  }
}
