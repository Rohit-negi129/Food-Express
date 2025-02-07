import { Component, Injectable, Input, OnInit } from '@angular/core';
import {Restaurant} from '../model/restaurant';

@Component({
  selector: 'app-restaurent-card',
  templateUrl: './restaurant-card.component.html',
  styleUrls: ['./restaurant-card.component.css']
})


export class RestaurentCardComponent implements OnInit {

  @Input()
  rest?: Restaurant;
  constructor() { }

  ngOnInit(): void {
    console.log(this.rest)
  }

  

}
