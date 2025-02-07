import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { FoodListComponent } from './food-list/food-list.component';
import { HomeComponent } from './home/home.component';
import { FoodcartComponent } from './foodcart/foodcart.component';
import { RegistrationComponent } from './registration/registration.component';
import { RestaurentListComponent } from './restaurent-list/restaurent-list.component';
import { AddRestaurantComponent } from './add-restaurant/add-restaurant.component';
import { AdminComponent } from './admin/admin.component';
import { AddFoodMenuComponent } from './add-food-menu/add-food-menu.component';
import { CheckRestaurantComponent } from './check-restaurant/check-restaurant.component';
import { DeleteRestaurantComponent } from './delete-restaurant/delete-restaurant.component';
import { DeleteFoodComponent } from './delete-food/delete-food.component';
import { FoodPageComponent } from './food-page/food-page.component';

const routes: Routes = [
  {path:'',component:HomeComponent},
  { path: 'restaurant', component: RestaurentListComponent },
  {path:'signin',component:LoginComponent},
  { path: 'restaurant/:id', component:FoodListComponent },
  { path:'cart', component:FoodcartComponent},
  {path:'register', component:RegistrationComponent},
  {path:'add-restaurant' , component:AddRestaurantComponent},
  {path:'admin' , component:AdminComponent},
  {path:'add-foodMenu' , component:AddFoodMenuComponent},
  {path:'check-restaurant' , component:CheckRestaurantComponent},
  {path:'delete-restaurant', component:DeleteRestaurantComponent},
  { path: 'delete-food', component: DeleteFoodComponent},
  {path:'search/:searchItem',component:HomeComponent}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
