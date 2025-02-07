import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { foodMenu } from 'src/app/model/foodMenu';
import { Restaurant } from 'src/app/model/restaurant';

@Injectable({
  providedIn: 'root'
})
export class RestaurantService {
  

  constructor(private http: HttpClient) {}
  apiUrl:string= 'http://localhost:9000/api/v3';

  getAllRestaurants(): Observable<Array<Restaurant>> {
    return this.http.get<Array<Restaurant>>(`${this.apiUrl}` + '/restaurants');
  }

  getRestaurant(restaurantId?: string): Observable<Restaurant> {
    return this.http.get<Restaurant>(`${this.apiUrl}/restaurants/${restaurantId}`);
  }

  getAllFoodMenu(restaurantId: string) {
    return this.http.get<foodMenu[]>(`${this.apiUrl}/restaurants/${restaurantId}/foodMenu`);
  }
  getFoodMenuById(itemId:number): Observable<foodMenu[]>{
    return this.http.get<foodMenu[]>(`${this.apiUrl}/foodMenu/${itemId}`);
  }

  addRestaurant(restaurant:any):Observable<Restaurant>{
    return this.http.post<Restaurant>(`${this.apiUrl}/restaurant/register`,restaurant);
  }
  addFoodItem(restaurantId:string ,foodMenu:foodMenu):Observable<any>{
    return this.http.post<foodMenu>(`${this.apiUrl}/${restaurantId}/add-food-menu`,foodMenu)
  }

  deleteRestaurant(restaurantId:string): Observable<any> {
    return this.http.delete(`${this.apiUrl}/delete-restaurant/${restaurantId}`);
  }
  deleteFoodItem(restaurantId: string, itemId: number): Observable<any> {
    const url = `${this.apiUrl}/restaurants/${restaurantId}/foodMenu/${itemId}`;
    return this.http.delete(url);
  }
  
}
