import { Router } from '@angular/router';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Post } from 'src/app/models/post';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { PostService } from 'src/app/services/post.service';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css'],
})
export class PostComponent implements OnInit {

  constructor(
    private postService: PostService,
    private authService: AuthService,
    private router: Router
  ) {}

  posts: Post[] = [];

  loggedInUser: User = new User();

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
      this.postService.index().subscribe({
        next: (postList) => {
          this.posts = postList;
        },
        error: (somethingBad) => {
          console.error('PostListComponent.reload: error loading posts');
          console.error(somethingBad);
        },
      });
    }
  }

  goToPost(postId: number) {
    this.router.navigateByUrl('posts/' + postId);
    this.postService.setVisiblePost(postId);
  }
  goToUserProfile(userId: number) {
    this.router.navigateByUrl('users/' + userId);
  }
}
