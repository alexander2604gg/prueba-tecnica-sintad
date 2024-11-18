import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private auth_url = "http://localhost:8080/api/v1/auth/login"

  constructor(private http: HttpClient) { }

  login (credentials: {userName : string , password : string}): Observable<any>{
    return this.http.post<any>(this.auth_url, credentials);
  }

  setToke (token: string) : void {
    localStorage.setItem('jwt' , token)
  }

  getToken () : string | null {
    return localStorage.getItem('jwt')
  }

  removeToken(): void {
    localStorage.removeItem('jwt');
  }

}
