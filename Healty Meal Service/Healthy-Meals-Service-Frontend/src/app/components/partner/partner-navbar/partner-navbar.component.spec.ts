import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PartnerNavbarComponent } from './partner-navbar.component';

describe('PartnerNavbarComponent', () => {
  let component: PartnerNavbarComponent;
  let fixture: ComponentFixture<PartnerNavbarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PartnerNavbarComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PartnerNavbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
