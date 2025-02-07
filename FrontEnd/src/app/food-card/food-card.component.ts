import { Component, Input, OnInit } from '@angular/core';
import { foodMenu } from '../model/foodMenu';
import { FoodcartService } from '../service/foodcartservice/foodcart.service';
import { Restaurant } from '../model/restaurant';

@Component({
  selector: 'app-food-card',
  templateUrl:'./food-card.component.html',
  styleUrls: ['./food-card.component.css']
})
export class FoodCardComponent implements OnInit {
  
  @Input() 
  foodItem?:foodMenu

  
  constructor(private cartService:FoodcartService) { }

  ngOnInit(): void {
    
  }
  addtocart(item: any){
    this.cartService.addtoCart(item);
  }
  // foodItem: foodMenu | undefined;
  // restaurant:any=[];
 
}
