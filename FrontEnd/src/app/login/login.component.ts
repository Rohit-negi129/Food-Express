import { Component, Input, OnInit } from '@angular/core';
 
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { AuthenticationServiceService } from '../service/Authentication-Service/authentication-service.service';
import { SessionLogService } from '../service/session-log.service';

import { TokenInterceptorService } from '../service/token-interceptor-service.service';
import { AlertServiceService } from '../service/alert-service.service';

 
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginform=new FormGroup({
    userEmail : new FormControl('', [Validators.required, Validators.pattern("[a-z0-9]+@[a-z]+\.[a-z]{2,3}")]),
    password: new FormControl("",[Validators.required,Validators.pattern("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,12}$")]),
   })
   get userEmail() {return this.loginform.get("userEmail")}
   get password() {return this.loginform.get("password")}
  //  get role(){
  //   return this.loginform.get('role');
  // }

   hide=true;
   loginMsg: boolean = false;
  //  @Input() restaurant?:Restaurant;
  constructor(private authenticationService:AuthenticationServiceService, private sessionLogService: SessionLogService,private router :Router,private tokenservice:TokenInterceptorService,private alertService: AlertServiceService) { }
  ngOnInit(): void {
     
    }
  
  signin(){
    const userDetails = {
      userEmail: this.loginform.value.userEmail,
      password: this.loginform.value.password
    }
    console.log(userDetails.userEmail+" hiii ")
    this.authenticationService.loginUser(userDetails).subscribe({
      next: data => {
        console.log(data);
        if (data) {
          localStorage.setItem('token', data);
          this.sessionLogService.login();
       const email= this.tokenservice.getUserEmail()
        if(email==='rohitnegi@gmail.com'){
          this.alertService.openSnackBar('Welcome Back');
          this.router.navigateByUrl("/admin");
        }
        else
        {
        
            this.alertService.openSnackBar('Welcome Back');  
          this.router.navigateByUrl("/restaurant");
        }
       
           
        }
      },
      error: () => {
        this.loginMsg = true;
      }
    });
  }

 

}
