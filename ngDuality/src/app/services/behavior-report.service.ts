import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { BehaviorReport } from '../models/behavior-report';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class BehaviorReportService {
  private baseUrl = 'http://localhost:8088/';
  public url = environment.baseUrl + 'api/behaviorReports';

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

  index(): Observable<BehaviorReport[]> {
    return this.http
      .get<BehaviorReport[]>(this.url, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () =>
              new Error('TodoService.create(): error creating todos: ' + err)
          );
        })
      );
  }

  create(behaviorReport: BehaviorReport): Observable<BehaviorReport> {
    return this.http
      .post<BehaviorReport>(this.url, behaviorReport, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () =>
              new Error(
                'BehaviorReportService.create(): error creating behaviorReports: ' +
                  err
              )
          );
        })
      );
  }
}
