import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Comment } from '../models/comment';
import { AuthService } from './auth.service';
// import { PostService } from './post.service';
// import { post } from 'jquery';

@Injectable({
  providedIn: 'root',
})
export class CommentService {
  private url = environment.baseUrl + 'api/comments';

  constructor(private http: HttpClient, private auth: AuthService) {}

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };

    return options;
  }

  index(): Observable<Comment[]> {
    return this.http.get<Comment[]>(this.url).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error(
              'CommentService.index(): error retrieving comments: ' + err
            )
        );
      })
    );
  }

  getPostComments(postId: number): Observable<Comment[]> {
    console.log('*** postId: ' + postId);
    return this.http
      .get<Comment[]>(this.url + '/posts/' + postId, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () =>
              new Error(
                'CommentService.getPostComments(): error retrieving comments: ' +
                  err
              )
          );
        })
      );
  }

  create(comment: Comment): Observable<Comment> {
    // this.auth.getLoggedInUser().subscribe({
    //   next: (user) => {
    //     comment.commentor = user;
    //   },
    //   error: (fail) => {
    //     console.error('ngOnInit(): Error getting user');
    //     console.error(fail);
    //   },
    // });

    return this.http.post<Comment>(this.url, comment, this.getHttpOptions()).pipe(

      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error('CommentService.create(): error creating comment: ' + err)
        );
      })
    );
  }

  update(updatedComment: Comment): Observable<Comment> {
    return this.http
      .put<Comment>(this.url + '/' + updatedComment.id, updatedComment,this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () =>
              new Error(
                'CommentService.update(): error updating comment: ' + err
              )
          );
        })
      );
  }

  destroy(commentId: number) {
    console.log('==2=='+commentId)
    return this.http.delete(this.url + "/" + commentId, this.getHttpOptions()).pipe(
    // return this.http.delete<void>(`${this.url}/${commentId}`).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error('CommentService.delete(): error deleting comment: ' + err)
        );
      })
    );
  }

  addCommentToPost(postId: number, comment: Comment): Observable<Comment> {
    return this.http
      .post<Comment>(
        environment.baseUrl + 'api/posts' + '/' + postId + '/comments',
        comment,
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () =>
              new Error(
                'AddCommentToPostService.AddCommentToPost(): error retrieving comments for posts: ' +
                  postId +
                  err
              )
          );
        })
      );
  }
}
