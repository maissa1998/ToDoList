import { Injectable } from '@angular/core';
import { UserResponseDTO } from '../../Models/UserResponseDTO';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { User } from '../../Models/User';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private apiUrl = `${environment.apiUrl}/auth`;

  constructor(private http: HttpClient) {}

  signup(user: User): Observable<UserResponseDTO> {
    return this.http.post<UserResponseDTO>(`${this.apiUrl}/signup`, user);
  }

  login(user: User): Observable<{ token: string; username: string }> {
    return this.http.post<{ token: string; username: string }>(`${this.apiUrl}/login`, user);
  }
}
