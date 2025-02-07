import { Component, OnInit } from '@angular/core';
import { foodMenu } from '../model/foodMenu';
import { FoodcartService } from '../service/foodcartservice/foodcart.service';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthenticationServiceService } from '../service/Authentication-Service/authentication-service.service';



@Component({
  selector: 'app-foodcart',
  templateUrl: './foodcart.component.html',
  styleUrls: ['./foodcart.component.css']
})
export class FoodcartComponent implements OnInit {
  public foods : any = [];
  public grandTotal!: number;
  constructor(private cartService : FoodcartService, public authService: AuthenticationServiceService){ }
  
  ngOnInit(): void {
    this.cartService.getProducts()
    .subscribe(res=>{
      this.foods = res;
      this.grandTotal = this.cartService.getTotalPrice();
    })
  }
  increaseQuantity(item: any) {
    item.quantity++;
    this.cartService.getProducts()
    .subscribe(res=>{
      this.foods = res;
      this.grandTotal = this.cartService.getTotalPrice();
    })
     // Increase quantity by 1
  }
  decreaseQuantity(item: any) {
    if (item.quantity > 1) {
      item.quantity--; 
      this.cartService.getProducts()
      .subscribe(res=>{
        this.foods = res;
        this.grandTotal = this.cartService.getTotalPrice();
      })// Decrease quantity by 1, if greater than 1
    }
  }
  removeItem(item: any){
    this.cartService.removeCartItem(item);
  }
  emptycart(){
    this.cartService.removeAllCart();
  }
  
}

