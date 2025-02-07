import { foodMenu} from "./foodMenu";

export type Restaurant ={
  restaurantId?: string;
  restaurantName?: string;
  description?: string;
  restaurantImage?: string;
  rating?:string;
  location?: string;
  foodMenu: foodMenu[];
}