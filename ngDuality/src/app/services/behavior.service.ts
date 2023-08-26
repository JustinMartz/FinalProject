import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Behavior } from '../models/behavior';
import { AuthService } from './auth.service';
import { BehaviorType } from '../models/behavior-type';
@Injectable({
  providedIn: 'root',
})
export class BehaviorService {
  private url = environment.baseUrl + 'api/behaviors';
  constructor(private http: HttpClient, private auth: AuthService) {}

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
  }
  index(): Observable<Behavior[]> {
    return this.http.get<Behavior[]>(this.url).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error(
              'BehaviorService.index(): error retrieving behaviors: ' + err
            )
        );
      })
    );
  }

  create(behavior: Behavior): Observable<Behavior> {
    behavior.severity = 0;
    behavior.name = '';
    behavior.behaviorType = new BehaviorType();

    return this.http.post<Behavior>(this.url, behavior).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error(
              'BehaviorService.create(): error creating behavior: ' + err
            )
        );
      })
    );
  }

  update(updatedBehavior: Behavior): Observable<Behavior> {
    return this.http
      .put<Behavior>(this.url + '/' + updatedBehavior.id, updatedBehavior)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () =>
              new Error(
                'BehaviorService.update(): error updating behavior: ' + err
              )
          );
        })
      );
  }

  destroy(behaviorId: number): Observable<void> {
    // return this.http.delete<void>(this.url + "/" + behaviorId).pipe(
    return this.http.delete<void>(`${this.url}/${behaviorId}`).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error(
              'BehaviorService.delete(): error deleting behavior: ' + err
            )
        );
      })
    );
  }
}
