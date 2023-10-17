import { TestBed } from '@angular/core/testing';

import { ReviewerGuardService } from './reviewer-guard.service';

describe('ReviewerGuardService', () => {
  let service: ReviewerGuardService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ReviewerGuardService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
