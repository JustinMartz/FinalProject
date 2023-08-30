import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';
import { Observable, catchError, throwError } from 'rxjs';
import { DirectMessage } from '../models/direct-message';
import { Post } from '../models/post';

@Injectable({
  providedIn: 'root'
})
export class DirectMessageService {

  private baseUrl = 'http://localhost:8083/';
    private url = environment.baseUrl + 'api/directMessages';

  constructor(private http: HttpClient, private auth: AuthService) { }

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  getDirectMessage(id: number): Observable<DirectMessage> {
    return this.http.get<DirectMessage>(this.url + '/' + id, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        return throwError(
          () => new Error(
              'PostService.getPost(): error retrieving Post: ' + err
          )
        );
      })
    );
  }

  getAllDirectMessages(id: number): Observable<DirectMessage[]> {
    return this.http.get<DirectMessage[]>(this.url + '/' + '/users/' + id, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error(
              'DirectMessages.getDirectMessages(): error retrieving DirectMessages: ' + err
            )
        );
      })
    );
  }





}
