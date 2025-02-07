import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SessionLogService {

  private readonly TOKEN_KEY = 'auth_token';
  private isUserLoggedIn: boolean = false;

  constructor() {
    this.isUserLoggedIn = localStorage.getItem(this.TOKEN_KEY) === 'true'; 
  }

  login() {
    this.isUserLoggedIn = true;
    localStorage.setItem(this.TOKEN_KEY, 'true');   // Store the authentication status in the local storage
  }

  logout() {
    this.isUserLoggedIn = false;
    localStorage.removeItem(this.TOKEN_KEY); // Clear the stored authentication status
  }
  
  isLoggedIn() {
    return this.isUserLoggedIn;
  }
}
