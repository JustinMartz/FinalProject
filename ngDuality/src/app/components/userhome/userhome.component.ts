import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-userhome',
  templateUrl: './userhome.component.html',
  styleUrls: ['./userhome.component.css']
})
export class UserhomeComponent implements OnInit {

  loggedInUser: User = new User();

  constructor (private authService: AuthService, private userService: UserService) { }

  ngOnInit():void {
    if (this.authService.checkLogin()) {
      this.authService.getLoggedInUser().subscribe( {
        next: (user) => {
          this.loggedInUser = user;
        },
        error: (fail) => {
          console.error('ngOnInit(): Error getting user');
          console.error(fail);
        }
      });
    }
  }
}
