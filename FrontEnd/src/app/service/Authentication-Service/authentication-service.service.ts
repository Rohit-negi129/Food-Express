import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TokenInterceptorService } from '../token-interceptor-service.service';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationServiceService {

  private isAuthenticated = false;
  public userEmail: string | null = null;
  private currentUser: any = null;
  // private readonly TOKEN_KEY = 'auth_token';
  // private isuserLoggedIn: boolean = false;
   
  constructor(private http:HttpClient , private tokenService :TokenInterceptorService) { }
  

  loginUser(user: any) {
    this.isAuthenticated = true;
    this.userEmail = this.tokenService.getUserEmail();

    return this.http.post(`http://localhost:9000/api/v1/login`, user, { responseType: 'text', withCredentials: true });
  }
   
  // isLoggedIn() {
  //   // Correcting the storage key used for token
  //   const token = localStorage.getItem(this.TOKEN_KEY);
  //   return !!token; // Simplified expression to check if token is truthy
  // }
  isAuthenticatedUser() {
    return this.isAuthenticated;
  }
  getUserEmail() {
    return this.userEmail;

  }
  setUserEmail(email: string | null): void {
    this.userEmail= email;
  }
  getUserName(): string {
    return this.currentUser ? this.currentUser.firstName : '';
  }

  logout() {
    this.isAuthenticated = false;
    this.userEmail = null;
  }
}