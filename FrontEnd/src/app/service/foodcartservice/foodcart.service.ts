import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { foodMenu } from 'src/app/model/foodMenu';

@Injectable({
  providedIn: 'root'
})
export class FoodcartService {

  public cartItemList : any =[]
  public productList = new BehaviorSubject<any>([]);
  public search = new BehaviorSubject<string>("");

  constructor() { }
  getProducts(){
    return this.productList.asObservable();
  }

  setProduct(foodMenu : any){
    this.cartItemList.push(...foodMenu);
    this.productList.next(foodMenu);
  }
  addtoCart(foodMenu: any) {
    foodMenu.quantity=1
    this.cartItemList.push(foodMenu);
  
    // Update the observable with the new cart items
    this.productList.next([...this.cartItemList]);
  

    // Additional actions with the cart items if needed
    console.log('Cart Items:', this.cartItemList);
  }
  
  getTotalPrice(): number {
    return this.cartItemList.reduce((grandTotal: number, currentItem: any) => {
      // Parse item price as a float
      const itemPrice = parseFloat(currentItem.price);

      // Check if itemPrice is a valid number
      if (!isNaN(itemPrice)) {
        grandTotal += itemPrice * currentItem.quantity; // Multiply by quantity
      }

      return grandTotal;
    }, 0);
  }
  
  
  removeCartItem(foodMenu: any) {
    const indexToRemove = this.cartItemList.findIndex((item: any) => foodMenu.id === item.id);
  
    if (indexToRemove !== -1) {
      this.cartItemList.splice(indexToRemove, 1);
      this.productList.next([...this.cartItemList]);
    }
  }
  
  
  
  removeAllCart(){
    this.cartItemList = []
    this.productList.next(this.cartItemList);
  }
}
