import { Component, Input, OnInit } from '@angular/core';
import { Post } from 'src/app/models/post';

import { PostService } from 'src/app/services/post.service';
import { Comment } from '../../models/comment';
import { CommentService } from 'src/app/services/comment.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css'],
})
export class CommentComponent implements OnInit {
  post: Post = new Post();
  comments: Comment[] = [];
  comment: Comment = new Comment();

  constructor(
    private commentService: CommentService,
    private postService: PostService,
    private router: Router
  ) {}

  ngOnInit(): void {
    let postId = this.postService.getVisiblePost();
    this.postService.getPost(postId).subscribe({
      next: (returnedPost) => {
        this.post = returnedPost;
      },
      error: (massiveFailure) => {
        console.error('Failed in CommentComponent.ngOnInit()');
        console.error(massiveFailure);
      },
    });
    console.log(this.post);
    this.commentService.getPostComments(postId).subscribe({
      next: (returnedComments) => {
        this.comments = returnedComments;
      },
      error: (massiveFailure) => {
        console.error('Failed in CommentComponent.ngOnInit()');
        console.error(massiveFailure);
      },
    });

    console.log(this.comments);
  }

  addCommentToPost(comment: Comment, id: number) {
    console.log(id);
    comment.post.id = this.post.id;
    this.commentService.addCommentToPost(id, comment).subscribe({
      next: (returnedComment) => {
        this.comments.push(returnedComment);
      },
      error: (massiveFailure) => {
        console.error('Failed in commentComponent.addCommentToPost()');
        console.error(massiveFailure);
      },
    });
  }

  deleteCommentFromPost(commentId: number) {
    console.log('==1==' + commentId);
    this.commentService.destroy(commentId).subscribe({
      next: (whatever) => {
        window.location.reload();
      },
      error: (massiveFailure) => {
        console.error('Failed in commentComponent.addCommentToPost()');
        console.error(massiveFailure);
      },
    });
  }
}
