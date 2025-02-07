import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../service/user-service/user.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent {

  constructor(private userService: UserService,private route : Router) {}
    userForm = new FormGroup({
      firstName: new FormControl('', Validators.required),
      lastName: new FormControl('', Validators.required),
      password:new FormControl('', [Validators.required, Validators.pattern(/^(?=.*\d)(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z]).{7,}$/)]),
      userEmail: new FormControl('', [Validators.required, Validators.pattern("[a-z0-9]+@[a-z]+\.[a-z]{2,3}")]),
    });
  
    get email(){
      return this.userForm.get('userEmail');
    }
  
    get password(){
      return this.userForm.get('password');
    }
    get role(){
      return this.userForm.get('role');
    }
    ngOnInit(): void {}
    onSubmit(): void {
      if (this.userForm.valid) {
        // Call your service to handle registration
        console.log(this.userForm.value)
        this.userService.registerUserData(this.userForm.value).subscribe(
          (response) => {
            console.log('User registration successful:', response);
            // Optionally, reset the form after successful registration
            this.userForm.reset();
            alert("user sucessfully register")
            this. route.navigateByUrl("") ;
          },
          (error) => {
            console.error('Error during user registration:', error);
          }
        );
      }
    }
  }


