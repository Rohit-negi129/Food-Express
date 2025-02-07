import { Component } from '@angular/core';
import { RestaurantService } from '../service/restaurant-service/restaurant.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-delete-food',
  templateUrl: './delete-food.component.html',
  styleUrls: ['./delete-food.component.css']
})
export class DeleteFoodComponent {
  deleteFoodForm: FormGroup;

  constructor(private foodService: RestaurantService, private router: Router, private fb: FormBuilder, private snackBar: MatSnackBar) {
    this.deleteFoodForm = this.fb.group({
      restaurantId: ['', Validators.required],
      itemId: ['', Validators.required]
    });
  }

  deleteFoodItem() {
    const { restaurantId, itemId } = this.deleteFoodForm.value;

    this.foodService.deleteFoodItem(restaurantId, itemId).subscribe(
      (response) => {
        console.log('Food item deleted successfully');
        this.snackBar.open('Food item deleted successfully', 'Close', {
          duration: 3000,
        });
        this.router.navigateByUrl("/admin");
      },
      (error) => {
        console.error('Error deleting food item', error);
        this.snackBar.open('Error: Food item not found or could not be deleted', 'Close', {
          duration: 3000,
        });
      }
    );
  }
}
