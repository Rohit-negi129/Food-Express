import { Component, OnInit } from '@angular/core';
import { foodMenu } from '../model/foodMenu';
import { Router } from '@angular/router';

@Component({
  selector: 'app-food-page',
  templateUrl: './food-page.component.html',
  styleUrls: ['./food-page.component.css']
})
export class FoodPageComponent implements OnInit {
food!:foodMenu
constructor(private router: Router,){}
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }
}
