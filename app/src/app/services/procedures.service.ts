import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { Observable } from 'rxjs';
import { CreateProcedureDto, PaginationParams, Procedures } from '../../types';

@Injectable({
  providedIn: 'root'
})
export class ProceduresService {
  url: string = '/procedures';

  constructor(private apiService: ApiService) { }

  getProcedures(params: PaginationParams): Observable<Procedures> {
    return this.apiService.get(this.url, {
      params,
      responseType: 'json',
    });
  }

  save(procedure: CreateProcedureDto) {
    return this.apiService.post(this.url, procedure, {
      responseType: 'json',
    });
  }
}
