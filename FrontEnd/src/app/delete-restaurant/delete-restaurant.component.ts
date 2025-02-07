import { Component } from '@angular/core';
import { RestaurantService } from '../service/restaurant-service/restaurant.service';
import { Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AlertServiceService } from '../service/alert-service.service'; // Import the AlertServiceService

@Component({
  selector: 'app-delete-restaurant',
  templateUrl: './delete-restaurant.component.html',
  styleUrls: ['./delete-restaurant.component.css']
})
export class DeleteRestaurantComponent {

  check: FormGroup = new FormGroup({
    restaurantId: new FormControl('', [Validators.required]),
  });

  get restaurantId() { return this.check.get("restaurantId"); }

  constructor(
    private restaurantService: RestaurantService,
    private router: Router,
    private alertService: AlertServiceService // Inject AlertServiceService
  ) {}

  deleteRestaurant() {
    const data = this.check.value;
    console.log(data);
  
    this.restaurantService.deleteRestaurant(data.restaurantId).subscribe(
      (response) => {
        console.log('Restaurant deleted successfully:', response);
        this.alertService.openSnackBar('Restaurant deleted successfully!'); // Notify success
        this.router.navigateByUrl("/admin");
      },
      (error) => {
        if (error.status === 404) {
          // Restaurant not found
          console.error('Restaurant not found:', error);
          this.alertService.openSnackBar('Error: Restaurant not found.');
        } else {
          // General error
          console.error('Error deleting restaurant:', error);
          this.alertService.openSnackBar('Error: Unable to delete restaurant.');
        }
      }
    );
  }
  
}
