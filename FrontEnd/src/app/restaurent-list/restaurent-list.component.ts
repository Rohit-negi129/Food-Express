import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Restaurant } from '../model/restaurant';
import { RestaurantService } from '../service/restaurant-service/restaurant.service';
import { foodMenu } from '../model/foodMenu';

@Component({
  selector: 'app-restaurent-list',
  templateUrl: './restaurent-list.component.html',
  styleUrls: ['./restaurent-list.component.css']
})
export class RestaurentListComponent implements OnInit {
  restaurants: Array<Restaurant>=[];
  filteredRestaurants: Array<Restaurant> = [];
  foodMenu:Array<foodMenu>=[];

  constructor(
    private restaurantService: RestaurantService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.restaurantService.getAllRestaurants().subscribe({
      next:data =>{
        this.restaurants = data;
      },
      error:err =>{
        alert(err);
      }
    })
    // this.restaurantService.getAllRestaurants().subscribe((result) => {
    //   this.restaurants = result;
    //   this.route.paramMap.subscribe((params) => {
    //     const restaurantId = params.get('id');
    //     this.getRestaurant(restaurantId);
    //   });
    // });
  }

  private getRestaurant(restaurantId: string | null): void {
    if (restaurantId) {
      // Fetch the details of the selected restaurant using getRestaurant
      this.restaurantService.getAllFoodMenu(restaurantId).subscribe((foodMenu) => {
        this.foodMenu = foodMenu;
      });
    } else {
      this.filteredRestaurants = this.restaurants;
    }
  }
}
