import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  constructor (private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    this.logout();
  }

  logout() {
    console.log('logout');
    this.authService.logout();
    if (!localStorage['credentials']) {
      this.router.navigateByUrl('/dualityhome');
    }
  }
}
