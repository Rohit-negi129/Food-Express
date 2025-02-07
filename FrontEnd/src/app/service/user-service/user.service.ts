import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  url:string='http://localhost:9000/api/v4/register';
  apiurl:string='http://localhost:9000/api/v4';
  
  constructor(private http:HttpClient) { }
  registerUserData(data:any)
  {
    console.log(data);
    return this.http.post(this.url,data)
  }
}
