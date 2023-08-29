import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Comment } from '../models/comment';
import { AuthService } from './auth.service';
import { PostService } from './post.service';
import { post } from 'jquery';

@Injectable({
  providedIn: 'root',
})
export class CommentService {
  private url = environment.baseUrl + 'api/comments';
  constructor(
    private http: HttpClient,
    private auth: AuthService,
    private postServ: PostService
  ) {}

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
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
    return this.http.get<Comment[]>(this.url + '/posts/' + postId).pipe(
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

  create(comment: Comment): Observable<Comment> {
    this.auth.getLoggedInUser().subscribe({
      next: (user) => {
        comment.creator = user;
      },
      error: (fail) => {
        console.error('ngOnInit(): Error getting user');
        console.error(fail);
      },
    });

    return this.http.post<Comment>(this.url, comment).pipe(
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
      .put<Comment>(this.url + '/' + updatedComment.id, updatedComment)
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

  destroy(commentId: number): Observable<void> {
    // return this.http.delete<void>(this.url + "/" + commentId).pipe(
    return this.http.delete<void>(`${this.url}/${commentId}`).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error('CommentService.delete(): error deleting comment: ' + err)
        );
      })
    );
  }
}
