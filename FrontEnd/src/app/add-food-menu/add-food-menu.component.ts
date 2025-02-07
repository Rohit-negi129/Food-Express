import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { RestaurantService } from '../service/restaurant-service/restaurant.service';
import { Router } from '@angular/router';
import { foodMenu } from '../model/foodMenu';
import { AlertServiceService } from '../service/alert-service.service';

@Component({
  selector: 'app-add-food-menu',
  templateUrl: './add-food-menu.component.html',
  styleUrls: ['./add-food-menu.component.css']
})
export class AddFoodMenuComponent {
  
  foodMenuForm: FormGroup = new FormGroup({
    restaurantId: new FormControl('', [Validators.required]),
    itemName: new FormControl('', [Validators.required]),
    itemId: new FormControl('', [Validators.required]),
    price: new FormControl('', [Validators.required]),
    foodImage: new FormControl('', [Validators.required]),
    description: new FormControl('', [Validators.required]) 
  });

  constructor(
    private restaurantService: RestaurantService,
    private router: Router,
    private alertService: AlertServiceService
  ) {}

  get restaurantId() { return this.foodMenuForm.get("restaurantId"); }
  get itemName() { return this.foodMenuForm.get("itemName"); }
  get itemId() { return this.foodMenuForm.get("itemId"); }
  get price() { return this.foodMenuForm.get("price"); }
  get foodImage() { return this.foodMenuForm.get("foodImage"); }
  get description() { return this.foodMenuForm.get("description"); }

  submit() {
    if (this.foodMenuForm.valid) {
      const data = this.foodMenuForm.value;

      this.restaurantService.getRestaurant(data.restaurantId).subscribe(
        res => {
          console.log('Restaurant found:', res);
          const newFoodMenu: foodMenu = {
            itemName: data.itemName,
            itemId: data.itemId,
            price: data.price,
            foodImage: data.foodImage,
            description: data.description,
          };

          this.restaurantService.addFoodItem(data.restaurantId, newFoodMenu).subscribe(
            (response) => {
              console.log('Food item added successfully:', response);
              this.alertService.openSnackBar('Food item added successfully!'); 
              this.foodMenuForm.reset();
              this.router.navigateByUrl("/add-foodMenu");
            },
            (error) => {
              console.error('Error adding food item:', error);
              this.alertService.openSnackBar('Error: Unable to add food item.'); // Notify failure
            }
          );
        },
        (error) => {
          console.error('Restaurant not found:', error);
          this.alertService.openSnackBar('Error: Restaurant not found.'); // Notify if restaurant doesn't exist
        }
      );
    }
  }
}
