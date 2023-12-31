import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { Observable, catchError, tap, throwError } from 'rxjs';
import { User } from '../models/user';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private url = environment.baseUrl + 'api/users';

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

  getUser(id: number): Observable<User> {
    return this.http.get<User>(this.url + '/' + id, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        return throwError(
          () => new Error(
              'UserService.getUser(): error retrieving User: ' + err
          )
        );
      })
    );
  }
  updateUser(updatedUser: User): Observable<User> {
    console.log("password in service "+updatedUser.password)
    let x = this.auth.generateBasicAuthCredentials(updatedUser.username, updatedUser.password);
    return this.http
      .put<User>(this.url + '/' + updatedUser.id, updatedUser,this.getHttpOptions())
      .pipe(tap( user => {
        localStorage.removeItem('credentials');
        let x = this.auth.generateBasicAuthCredentials(user.username, user.password);
        localStorage.setItem('credentials', x);
      })
        ,
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () =>
              new Error(
                'UserService.update(): error updating user: ' + err
              )
          );
        })
      );
  }
}
