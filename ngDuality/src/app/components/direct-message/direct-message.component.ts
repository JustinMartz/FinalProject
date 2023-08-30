import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { DirectMessage } from 'src/app/models/direct-message';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { DirectMessageService } from 'src/app/services/direct-message.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-direct-message',
  templateUrl: './direct-message.component.html',
  styleUrls: ['./direct-message.component.css'],
})
export class DirectMessageComponent {
  constructor(
    private authService: AuthService,
    private dmService: DirectMessageService,
    private router: Router,
    private userService: UserService
  ) {}
  sender: User = new User();
  senderId: number = 0;
  loggedInUser: User = new User();
  dm: DirectMessage = new DirectMessage();
  dmId: number = 0;
  userMessages: DirectMessage[] = [];

  ngOnInit(): void {
    if (this.authService.checkLogin()) {
      this.authService.getLoggedInUser().subscribe({
        next: (user) => {
          this.loggedInUser = user;
        },
        error: (fail) => {
          console.error('ngOnInit(): Error getting user');
          console.error(fail);
        },
      });
    }

    this.dmService.getAllDirectMessages(this.loggedInUser.id).subscribe({
      next: (directMessages) => {
        this.userMessages = directMessages;
      },
      error: (fail) => {
        console.error('ngOnInit(): Error getting user');
        console.error(fail);
      },
    });

    this.userService.getUser(this.dm.sender.id).subscribe({
      next: (user) => {
        this.sender = user;
      },
      error: (somethingBad) => {
        console.error('PostListComponent.reload: error loading posts');
        console.error(somethingBad);
      },
    });
  }
}
