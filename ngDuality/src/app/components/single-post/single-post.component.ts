import { AuthService } from './../../services/auth.service';
import { PostService } from 'src/app/services/post.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { Post } from 'src/app/models/post';
import { Comment } from '../../models/comment';
import { CommentService } from 'src/app/services/comment.service';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-single-post',
  templateUrl: './single-post.component.html',
  styleUrls: ['./single-post.component.css'],
})
export class SinglePostComponent implements OnInit {
  post: Post = new Post();
  loggedInUser: User = new User();

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private postService: PostService,
    private commentService: CommentService,
    private authService: AuthService
  ) {}

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

    this.activatedRoute.paramMap.subscribe({
      next: (params: ParamMap) => {
        let postIdParam = params.get('postId');
        if (postIdParam) {
          let postId = parseInt(postIdParam); // param values are always strings
          if (isNaN(postId)) {
            this.router.navigateByUrl('invalidPostId'); // Undefined path will match wildcard
          } else {
            this.postService.setVisiblePost(postId);
            this.postService.getPost(postId).subscribe({
              next: (post) => {
                this.post = post;
              },
              error: (fail) => {
                console.error('ngOnInit(): Error getting post');
                console.error(fail);
              },
            });
          }
        }
      },
    });
  }

  deletePost(postId: number) {
    this.postService.destroy(postId);
  }
}
