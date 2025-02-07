
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { jwtDecode } from 'jwt-decode';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor {

 
  constructor() { }
  // intercept(req, next) {
  //   const token = localStorage.getItem('token');

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = localStorage.getItem('token');

    let tokenReq = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
    return next.handle(tokenReq);
  }

  

  getRole(): string | null {
    const token = localStorage.getItem('token');
  
    // Check if token is null before attempting to decode
    if (token === null) {
      return null;
    }
  
    const decodedToken: { [key: string]: any } = jwtDecode(token);
  
    // Use type assertion for 'role' property if you are sure it will always be a string
    const role = decodedToken?.['role'] as string | undefined;
  
    // Return null if 'role' is undefined
    return role ?? null;
  }
  


  getUserEmail(): string | null {
    const token = localStorage.getItem('token');
  
    // Check if token is null before attempting to decode
    if (token === null) {
      return null;
    }
  
    const decodedToken: { [key: string]: any } = jwtDecode(token);
  
    // Use type assertion for 'sub' property if you are sure it will always be a string
    const userEmail = decodedToken?.['sub'] as string | undefined;
  
    // Return null if 'sub' is undefined
    return userEmail ?? null;
  }
  
  }
































// import { HttpInterceptor } from '@angular/common/http';
// import { Injectable } from '@angular/core';
// import {jwtDecode } from 'jwt-decode'

// @Injectable({
//   providedIn: 'root'
// })
// export class TokenInterceptorServiceService implements HttpInterceptor {

//   constructor() { }
//   intercept(req, next) {
//     const token = localStorage.getItem('token');

//     let tokenReq = req.clone({
//       setHeaders: {
//         Authorization: `Bearer ${token}`
//       }
//     });
//     return next.handle(tokenReq);
//   }

//   // getUserName(): string {
//   //   const token = localStorage.getItem('token');
//   //   const decodedToken: { [key: string]: any } = jwtDecode(token);//(npm install jwt-decode )package

//   //   const userName = decodedToken?.['UserName'];w

//   //   return userName;
//   // }

//   getRole():string{
//     const token=localStorage.getItem('token');
//     const decodedToken: { [key: string]: any } = jwtDecode(token);
//     const role=decodedToken?.['role'];
//     return role;
//   }

//   getUserEmail():string{
//     const token=localStorage.getItem('token');
//     const decodedToken: { [key: string]: any } = jwtDecode(token);
//     const userEmail=decodedToken?.['sub'];
//     return userEmail;
//   }
// }
