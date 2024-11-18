import { Injectable } from '@angular/core';
import { HttpClient, HttpParams , HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Page} from '../models/page';
import { EntityModel } from '../models/entityModel';
import { UrlMethod } from '../models/urlMethod';
import { EntidadResponseDto } from '../models/entidadResponseDto';


@Injectable({
  providedIn: 'root'
})
export class EntidadService {

  private entidades_url = 'http://localhost:8080/api/v1/entidades';

  constructor(private http: HttpClient) { }

  getEntities(page: number = 0, size: number = 5): Observable<Page<EntityModel<EntidadResponseDto>>> {
    const token = localStorage.getItem('jwt');

    let headers = new HttpHeaders();
    if (token) {
      headers = headers.set('Authorization', `Bearer ${token}`);
    }

    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());

    return this.http.get<Page<EntityModel<EntidadResponseDto>>>(this.entidades_url, {
      headers: headers,
      params: params
    });
  }


  handleRequest(urlMethod: UrlMethod , payLoad : {}): void {
    const url = urlMethod.url;
    const method = urlMethod.method;

    if (method === 'PATCH') {
      this.patchRequest(url.toString() , payLoad);
    }
    else if (method === 'GET') {
      this.getRequest(url.toString());
    }
    else if (method === 'POST') {
      this.postRequest(url.toString() , payLoad);
    }
  }

  patchRequest(url: string , payload : {}): void {
    this.http.patch(url, payload).subscribe(response => {
      console.log('PATCH Response:', response);
    });
  }


  getRequest(url: string): void {
    this.http.get(url).subscribe(response => {
      console.log('GET Response:', response);
    });
  }


  postRequest(url: string , payload : {}): void {
    this.http.post(url, payload).subscribe(response => {
      console.log('POST Response:', response);
    });
  }


}
