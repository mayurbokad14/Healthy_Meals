import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PartnerHomeComponent } from './partner-home.component';

describe('PartnerHomeComponent', () => {
  let component: PartnerHomeComponent;
  let fixture: ComponentFixture<PartnerHomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PartnerHomeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PartnerHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
