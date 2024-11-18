import { EntityModel } from './../models/entityModel';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TipoDocumentoResponseDto } from '../models/tipoDocumentoDto';

@Injectable({
  providedIn: 'root'
})
export class TipoDocumentoService {

  private tipo_documento_url = 'http://localhost:8080/api/v1/tipo-documentos';

  constructor(private http: HttpClient) { }


  getAllTiposContribuyentes(): Observable<EntityModel<TipoDocumentoResponseDto>[]> {
    return this.http.get<EntityModel<TipoDocumentoResponseDto>[]>(this.tipo_documento_url);
  }
}
