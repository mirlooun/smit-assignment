import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Options } from '../../types';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  baseUrl = import.meta.env.NG_APP_BASE_API_URL;

  constructor(private httpClient: HttpClient) { }

  get<T>(url: string, options: Options): Observable<T> {
    return this.httpClient.get<T>(this.baseUrl + url, options) as Observable<T>;
  }

  post<T>(url: string, payload: T, options: Options): Observable<T> {
    return this.httpClient.post<T>(this.baseUrl + url, payload, options) as Observable<T>;
  }
}
