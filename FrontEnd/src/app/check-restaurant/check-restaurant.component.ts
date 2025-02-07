import { Component } from '@angular/core';
import { Restaurant } from '../model/restaurant';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { RestaurantService } from '../service/restaurant-service/restaurant.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-check-restaurant',
  templateUrl: './check-restaurant.component.html',
  styleUrls: ['./check-restaurant.component.css']
})
export class CheckRestaurantComponent {

  restaurant: Restaurant = {
    foodMenu: [],
  };

  check: FormGroup = new FormGroup({
    restaurantId: new FormControl('', [Validators.required]),
  });

  get restaurantId() {
    return this.check.get("restaurantId");
  }

  constructor(private restaurantService: RestaurantService, private router: Router) {}

  submit() {
    const data = this.check.value;
    console.log(data);

    // Calling the service to check if the restaurant exists
    this.restaurantService.getRestaurant(data.restaurantId).subscribe(
      (res: Restaurant) => {
        console.log(res);

        // If the restaurant exists, navigate to the add-foodMenu page
        if (res) {
          this.router.navigateByUrl("/add-foodMenu");
        }
      },
      (error) => {
        // Handle error scenario, such as when restaurant is not found
        console.error('Error fetching restaurant:', error);

        // Display alert and prevent navigation if restaurant is not found
        alert("Restaurant with this ID does not exist. Please check and try again.");
      }
    );
  }
}
