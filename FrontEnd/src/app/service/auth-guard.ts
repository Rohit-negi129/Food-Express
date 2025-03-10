import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { LoginComponent } from '../login/login.component';
import { AuthenticationServiceService } from './Authentication-Service/authentication-service.service';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationguardGuard implements CanActivate {

  constructor(private router:Router,private authService:AuthenticationServiceService,public dialog: MatDialog){}
  isLoggedIn:Boolean=false
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      if(this.authService.isLogin()){
        return true;
      }else{
        this.router.navigate(["/"])
        this.dialog.open(LoginComponent) ;
        return false;
      }
  }
  
}
