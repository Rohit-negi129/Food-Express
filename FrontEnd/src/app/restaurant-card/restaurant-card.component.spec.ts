import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RestaurentCardComponent } from './restaurant-card.component';

describe('RestaurentCardComponent', () => {
  let component: RestaurentCardComponent;
  let fixture: ComponentFixture<RestaurentCardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RestaurentCardComponent]
    });
    fixture = TestBed.createComponent(RestaurentCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
