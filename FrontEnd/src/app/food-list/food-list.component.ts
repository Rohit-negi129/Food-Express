import { Component, OnInit } from '@angular/core';
import { RestaurantService } from '../service/restaurant-service/restaurant.service';
import { ActivatedRoute } from '@angular/router';
import { Restaurant } from '../model/restaurant';
import { foodMenu } from '../model/foodMenu';

@Component({
  selector: 'app-food-list',
  templateUrl: './food-list.component.html',
  styleUrls: ['./food-list.component.css']
})
export class FoodListComponent implements OnInit{

  restaurant: Restaurant | undefined;
  foodMenu: foodMenu[] = []


  constructor(private restaurantService:RestaurantService, private route: ActivatedRoute ){}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const restaurantId = params.get('id');

      if (restaurantId) {
        // Fetch the restaurant details
        this.restaurantService.getRestaurant(restaurantId).subscribe(restaurant => {
          this.restaurant = restaurant;
          
          // Fetch the food menu for the specific restaurant
          this.restaurantService.getAllFoodMenu(restaurantId).subscribe(foodMenu => {
            console.log('Food List:', foodMenu);
           this.foodMenu = foodMenu;
           // this.foodMenu=foodMenuData.map(foodItem=> new foodMenu(foodItem.itemId,foodItem.itemName,foodItem.foodImage,foodItem.description,foodItem.price));
          });
        });
      }
    });
  }
}






//     this.route.paramMap.subscribe((params) => {
//   let restaurantId = params.get('restaurantId');

//       console.log('inside food menu')
//       console.log(restaurantId);
//     if (restaurantId) {
//       this.restaurantId = restaurantId;
//       this.restaurantService.getAllFoodMenu(this.restaurantId).subscribe(foodMenu => {
//         console.log('Food List:', foodMenu);
//         this.foodMenu = foodMenu;
//         console.log(this.foodMenu);
//           });
//       }
  
//   });
// }

//   this.restaurantService.getFoodsForRestaurantId(this.restaurantId).subscribe({
//     next:data =>{
//       this.foodMenu = data;
//     },
//     error:err =>{
//       alert(err);
//     }
//   })
//   this.restaurantService.getFoodsForRestaurantId(this.restaurantId).subscribe((result) => {
//     this.foodMenu= result;
//     this.route.paramMap.subscribe((params) => {
//       const foods = params.get('id');
//     //   this.get(restaurantId);
//     });
//   });
// });
// }

