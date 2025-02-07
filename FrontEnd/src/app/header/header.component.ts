import { Component } from '@angular/core';
import { FoodcartComponent } from '../foodcart/foodcart.component';
import { FoodcartService } from '../service/foodcartservice/foodcart.service';
import { AuthenticationServiceService } from '../service/Authentication-Service/authentication-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
[x: string]: any;
  userEmail: string | null = null;

  public totalItem : number = 0;
  public searchTerm !: string;
  constructor(private cartService : FoodcartService ,public authService:AuthenticationServiceService ,private route:Router) {
    this.userEmail = this.authService.getUserEmail();

   }

  ngOnInit(): void {
    this.cartService.getProducts()
    .subscribe(res=>{
      this.totalItem = res.length;
    })
  }
  search(event:any){
    this.searchTerm = (event.target as HTMLInputElement).value;
    console.log(this.searchTerm);
    this.cartService.search.next(this.searchTerm);
  }
  logout() {
    this.authService.logout();
    this.route.navigateByUrl('/signin');
  }
}
