import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Resource } from 'src/app/models/resource';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { ResourceService } from 'src/app/services/resource.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-userhome',
  templateUrl: './userhome.component.html',
  styleUrls: ['./userhome.component.css'],
})
export class UserhomeComponent implements OnInit {
  loggedInUser: User = new User();

  userResources: Resource[] | undefined;
  resource: Resource = new Resource();

  openAccordian: Boolean = false;

  constructor(
    private authService: AuthService,
    private resourceService: ResourceService,
    private userService: UserService,
    private router: Router
  ) {}

  ngOnInit(): void {
    if (this.authService.checkLogin()) {
      this.authService.getLoggedInUser().subscribe({
        next: (user) => {
          this.loggedInUser = user;

          this.loadUserResources();
        },
        error: (fail) => {
          console.error('ngOnInit(): Error getting user');
          console.error(fail);
        },
      });
    }
  }

  addResource() {
    this.resource.active = true;

    this.resourceService.create(this.resource).subscribe({
      next: (createdPost) => {
        this.resource = new Resource();

        this.reload();
      },
      error: (fail) => {
        console.error('createComponent.addPost: error creating post');
        console.error(fail);
      },
    });
  }

  reload() {}

  toggleAccordian() {
    if (this.openAccordian) {
      this.openAccordian = false;
    } else {
      this.openAccordian = true;
    }
  }
  updateUser() {
<<<<<<< HEAD
    console.log('updated loggedInUser: ' + this.loggedInUser.username);
    console.log("password in component " +this.loggedInUser.password)
    this.userService.updateUser(this.loggedInUser).subscribe({
      next: (user) => {
        // this.authService.getLoggedInUser().subscribe({});
        this.authService.getLoggedInUser().subscribe({
          next: (foo) => {
            this.loggedInUser = foo;
            console.log('^^^^^^^^ loggedInUser getting set to : ' + foo.username);
          },
          error: (msg) => {
            console.error('Error in UserhomeComponent.updateUser()');
          }
        });
        console.log('********************** returned user' + user);

=======
    console.log('password in component ' + this.loggedInUser.password);
    this.userService.updateUser(this.loggedInUser).subscribe({
      next: (user) => {
        this.authService
          .login(this.loggedInUser.username, this.loggedInUser.password)
          .subscribe({
            next: (liu) => {
              liu.password = this.loggedInUser.password;

              this.loggedInUser = liu;
              this.loadUserResources();
              // this.router.navigateByUrl('/userhome');
              // window.location.reload();
            },
            error: (problem) => {
              console.error('LoginComponent.login(): Error logging in user:');
              console.error(problem);
            },
          });
>>>>>>> 518188538365de7c9f03357a8a6102e03587fdb1
      },

      error: (fail) => {
        console.error('UpdateUser.updateUser(): error updating user');
        console.error(this.loggedInUser);
      },
    });
  }

  loadUserResources() {
    this.resourceService.getUserResources(this.loggedInUser.id).subscribe({
      next: (resources) => {
        this.userResources = resources;
      },
      error: (fail) => {
        console.error('ngOnInit(): Error getting resources');
        console.error(fail);
      },
    });
  }
}
