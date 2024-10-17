import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PartnerMealsComponent } from './partner-meals.component';

describe('PartnerMealsComponent', () => {
  let component: PartnerMealsComponent;
  let fixture: ComponentFixture<PartnerMealsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PartnerMealsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PartnerMealsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
