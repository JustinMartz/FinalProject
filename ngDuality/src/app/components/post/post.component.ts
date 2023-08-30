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

  personalPosts: Post[] = [];
  nonPersonalPosts: Post[] = [];
  newPost: Post = new Post();

  loggedInUser: User = new User();

  ngOnInit(): void {
    this.authService.getLoggedInUser().subscribe({
      next: (user) => {
        this.loggedInUser = user;
      },
      error: (fail) => {
        console.error('ngOnInit(): Error getting user');
        console.error(fail);
      },
    });
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
          for (let p of postList) {
            if (!p.personal) {
              this.nonPersonalPosts.push(p);
            } else {
              this.personalPosts.push(p);
            }
          }
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
  createPost(post: Post) {
    post.active = true;
    post.anonymous = false;

    this.postService.create(post).subscribe({
      next: (createdPost) => {
        this.newPost = new Post();
        this.reload();
      },
      error: (fail) => {
        console.error('createComponent.addPost: error creating post');
        console.error(fail);
      },
    });
  }

  reload() {
    this.postService.index().subscribe({
      next: (postList) => {
        this.nonPersonalPosts=[];
        this.personalPosts=[];
        for (let p of postList) {
          if (!p.personal) {
            this.nonPersonalPosts.push(p);
          } else {
            this.personalPosts.push(p);
          }
        }
      },
      error: (somethingBad) => {
        console.error('PostComponent.reload: error loading posts');
        console.error(somethingBad);
      },
    });
  }
}
