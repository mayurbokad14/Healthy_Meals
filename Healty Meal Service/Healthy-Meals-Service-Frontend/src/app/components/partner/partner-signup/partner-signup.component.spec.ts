import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PartnerSignupComponent } from './partner-signup.component';

describe('PartnerSignupComponent', () => {
  let component: PartnerSignupComponent;
  let fixture: ComponentFixture<PartnerSignupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PartnerSignupComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PartnerSignupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
