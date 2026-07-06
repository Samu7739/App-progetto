import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { CategoriaMuscolare } from '../models/categoria-muscolare.model/categoria-muscolare.model';

@Injectable({
  providedIn: 'root'
})
export class CategoriaService {

  private readonly apiUrl = `${window.location.protocol}//${window.location.hostname}:8080/api/categorie`;

  constructor(private http: HttpClient) {}

  getCategorie(): Observable<CategoriaMuscolare[]> {
    return this.http.get<CategoriaMuscolare[]>(this.apiUrl);
  }
}