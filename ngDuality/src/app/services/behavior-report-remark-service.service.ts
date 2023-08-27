import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { BehaviorReportRemark } from '../models/behavior-report-remark';

@Injectable({
  providedIn: 'root'
})
export class BehaviorReportRemarkService {
private baseUrl = 'http://localhost:8088/';
private url = environment.baseUrl + 'api/behaviorReportRemarks'

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

  index(): Observable<BehaviorReportRemark[]> {

    return this.http.get<BehaviorReportRemark[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('TodoService.create(): error creating todos: ' + err)
        );
      })
    );

  }


create(behaviorReportRemark: BehaviorReportRemark): Observable<BehaviorReportRemark> {
     return this.http.post<BehaviorReportRemark>(this.url, behaviorReportRemark, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('BehaviorReportRemarkService.create(): error creating behaviorReportRemarks: ' + err)
        );
      })
    );


  }

}
