import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RestaurentListComponent } from './restaurent-list.component';

describe('RestaurentListComponent', () => {
  let component: RestaurentListComponent;
  let fixture: ComponentFixture<RestaurentListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RestaurentListComponent]
    });
    fixture = TestBed.createComponent(RestaurentListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
