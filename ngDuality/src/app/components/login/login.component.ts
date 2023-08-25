import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  logInUser: User = new User();

  constructor(private authService: AuthService, private router: Router) {}

  login(user: User) {
    this.authService.login(user.username, user.password).subscribe({
      next: (loggedInUser) => {
        this.router.navigateByUrl('/userhome');
      },
      error: (problem) => {
        console.error('LoginComponent.login(): Error logging in user:');
        console.error(problem);
      }
    });
  }
}
