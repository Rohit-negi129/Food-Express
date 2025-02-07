import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FooterComponent } from './footer/footer.component';
import { HttpClientModule } from '@angular/common/http';
import { NZ_I18N } from 'ng-zorro-antd/i18n';
import { en_US } from 'ng-zorro-antd/i18n';
import { registerLocaleData } from '@angular/common';
import en from '@angular/common/locales/en';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './login/login.component';
import { RestaurentCardComponent } from './restaurant-card/restaurant-card.component';
import { RestaurentListComponent } from './restaurent-list/restaurent-list.component';
import { FoodCardComponent } from './food-card/food-card.component';
import { FoodListComponent } from './food-list/food-list.component';

import { MatToolbarModule } from '@angular/material/toolbar'
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatChipsModule } from '@angular/material/chips';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatTableModule } from '@angular/material/table';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { HomeComponent } from './home/home.component';
import { FoodcartComponent } from './foodcart/foodcart.component';
import { NgToastModule } from 'ng-angular-popup';
import { RegistrationComponent } from './registration/registration.component';
import { AddRestaurantComponent } from './add-restaurant/add-restaurant.component';
import { AddFoodMenuComponent } from './add-food-menu/add-food-menu.component';
import { AdminComponent } from './admin/admin.component';
import { CheckRestaurantComponent } from './check-restaurant/check-restaurant.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { DeleteRestaurantComponent } from './delete-restaurant/delete-restaurant.component';
import { DeleteFoodComponent } from './delete-food/delete-food.component';
import { FoodPageComponent } from './food-page/food-page.component';

registerLocaleData(en);

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    LoginComponent,
    RestaurentCardComponent,
    RestaurentListComponent,
    FoodCardComponent,
    FoodListComponent,
    HomeComponent,
    FoodcartComponent,
    RegistrationComponent,
    AddRestaurantComponent,
    AddFoodMenuComponent,
    AdminComponent,
    CheckRestaurantComponent,
    PageNotFoundComponent,
    DeleteRestaurantComponent,
    DeleteFoodComponent,
    FoodPageComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatIconModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatToolbarModule,
    MatInputModule,
    MatTooltipModule,
    MatCardModule,
    MatButtonModule,
    MatTableModule,
    MatNativeDateModule,
    MatDatepickerModule,
    MatButtonToggleModule,
    MatSnackBarModule,
    MatChipsModule,
    NgToastModule
  ],
  providers: [
    { provide: NZ_I18N, useValue: en_US }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
