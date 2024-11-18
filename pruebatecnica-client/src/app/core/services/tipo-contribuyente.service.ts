import { EntityModel } from './../models/entityModel';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TipoContribuyenteResponseDto } from '../models/tipoContribuyenteResponseDto';

@Injectable({
  providedIn: 'root'
})
export class TipoContribuyenteService {


  private tipo_contribuyente_url = 'http://localhost:8080/api/v1/tipo-contribuyentes';

  constructor(private http: HttpClient) { }


  getAllTiposContribuyentes(): Observable<EntityModel<TipoContribuyenteResponseDto>[]> {
    return this.http.get<EntityModel<TipoContribuyenteResponseDto>[]>(this.tipo_contribuyente_url);
  }

}
