import { Component, Input, OnInit } from '@angular/core';
import { Post } from 'src/app/models/post';

import { PostService } from 'src/app/services/post.service';
import { Comment } from '../../models/comment'
import { CommentService } from 'src/app/services/comment.service';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit {

  post: Post = new Post();
  comments: Comment[] = [];

  constructor(private commentService: CommentService, private postService: PostService) { }

  ngOnInit(): void {
    let postId = this.postService.getVisiblePost();

    this.commentService.getPostComments(postId).subscribe({
      next: (returnedComments) => {
        this.comments = returnedComments;
      },
      error: (massiveFailure) => {
        console.error('Failed in CommentComponent.ngOnInit()');
        console.error(massiveFailure);
      }
    });

    console.log(this.comments);
  }

}
