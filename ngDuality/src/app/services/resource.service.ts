import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';
import { Resource } from '../models/resource';
import { Observable, catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ResourceService {

  private url = environment.baseUrl + 'api/resources';

  constructor(private http: HttpClient, private auth: AuthService) {}

  getAllResources(): Observable<Resource[]> {
    return this.http.get<Resource[]>(this.url).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error(
              'ResourceService.getAllResources(): error retrieving Resources: ' + err
            )
        );
      })
    );
  }

  getUserResources(userId: number): Observable<Resource[]> {
    return this.http.get<Resource[]>(this.url + '/' + userId).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error(
              'ResourceService.getUserResources(): error retrieving Resources for user: ' +userId + err
            )
        );
      })
    );
  }
}
