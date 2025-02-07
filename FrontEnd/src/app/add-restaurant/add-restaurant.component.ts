import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RestaurantService } from '../service/restaurant-service/restaurant.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-add-restaurant',
  templateUrl: './add-restaurant.component.html',
  styleUrls: ['./add-restaurant.component.css']
})
export class AddRestaurantComponent {
  registration: FormGroup;

  constructor(
    private fb: FormBuilder,
    private restaurantService: RestaurantService,
    private router: Router,
    private snackBar: MatSnackBar
  ) {
    this.registration = this.fb.group({
      restaurantId: ['', Validators.required],
      restaurantName: ['', Validators.required],
      description: ['', Validators.required],
      restaurantImage: ['', Validators.required],
      location: ['', Validators.required]
    });
  }

  submit() {
    if (this.registration.valid) {
      this.restaurantService.addRestaurant(this.registration.value).subscribe(
        (response) => {
          console.log('Restaurant added successfully');
          this.snackBar.open('Restaurant added successfully!', 'Close', {
            duration: 3000,
          });
          this.router.navigateByUrl("/admin");
        },
        (error) => {
          console.error('Error adding restaurant', error);
          this.snackBar.open('Error: Unable to add restaurant.', 'Close', {
            duration: 3000,
          });
        }
      );
    }
  }
}
