import { Component } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { Resource } from 'src/app/models/resource';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent {
  loggedInUser: User = new User();
  user: User = new User();
  userResources: Resource[] | undefined;

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private userService: UserService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe({
      next: (params: ParamMap) => {
        let userIdParam = params.get('userId');
        if (userIdParam) {
          let userId = parseInt(userIdParam); // param values are always strings
          if (isNaN(userId)) {
            this.router.navigateByUrl('invalidPostId'); // Undefined path will match wildcard
          } else {
            this.userService.getUser(userId).subscribe({
              next: (user) => {
                this.user = user;
                this.userResources = user.resources;
              },
              error: (fail) => {
                console.error('ngOnInit(): Error getting user');
                console.error(fail);
              },
            });
          }
        }
      },
    });
  }



}
