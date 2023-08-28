import { Injectable } from '@angular/core';
import { Post } from '../models/post';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { DatePipe } from '@angular/common';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class PostService {

  private baseUrl = 'http://localhost:8083/';
private url = environment.baseUrl + 'api/posts';

  constructor(private http: HttpClient, private datePipe: DatePipe, private auth: AuthService) {}



  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  index(): Observable<Post[]> {

    return this.http.get<Post[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('PostService.create(): error creating posts: ' + err)
        );
      })
    );

  }

  create(post: Post): Observable<Post> {
  this.auth.getLoggedInUser().subscribe(
    {
      next: (user) => {
        post.creator = user;
      },
      error: (fail) => {
        console.error('ngOnInit(): Error getting user');
        console.error(fail);
      }
    }
  );


    return this.http.post<Post>(this.url, post, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('PostService.create(): error creating posts: ' + err)
        );
      })
    );


  }

  generateId() {
    // return this.posts[this.posts.length - 1].id + 1;
  }


  // update(updatePost: Post): Observable<Post> {
  //   if(updatePost.completed) {
  //     updatePost.completeDate = this.datePipe.transform(Date.now(), 'shortDate');
  //   }
  //   else {
  //     updatePost.completeDate = '';
  //   }
  //   return this.http.put<Post>(this.url + "/" + updatePost.id, updatePost).pipe(
  //     catchError((err: any) => {
  //       console.log(err);
  //       return throwError(
  //         () => new Error('PostService.create(): error creating posts: ' + err)
  //       );
  //     })
  //   );


  // }

  // destroy(postId: number) {
  //   return this.http.delete<Post>(this.url + "/" + postId, this.getHttpOptions()).pipe(

  //     catchError((err: any) => {
  //       console.log(err);
  //       return throwError(
  //         () => new Error('PostService.delete(): error deleting posts: ' + err)
  //       );
  //     })
  //   );
  // }
}
