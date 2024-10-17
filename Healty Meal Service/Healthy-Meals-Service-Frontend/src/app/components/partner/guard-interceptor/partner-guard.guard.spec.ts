import { TestBed } from '@angular/core/testing';

import { PartnerGuardGuard } from './partner-guard.guard';

describe('PartnerGuardGuard', () => {
  let guard: PartnerGuardGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(PartnerGuardGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
