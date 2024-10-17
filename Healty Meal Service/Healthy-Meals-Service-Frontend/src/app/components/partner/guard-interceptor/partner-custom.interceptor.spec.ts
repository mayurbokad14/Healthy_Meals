import { TestBed } from '@angular/core/testing';

import { PartnerCustomInterceptor } from './partner-custom.interceptor';

describe('PartnerCustomInterceptor', () => {
  beforeEach(() => TestBed.configureTestingModule({
    providers: [
      PartnerCustomInterceptor
      ]
  }));

  it('should be created', () => {
    const interceptor: PartnerCustomInterceptor = TestBed.inject(PartnerCustomInterceptor);
    expect(interceptor).toBeTruthy();
  });
});
