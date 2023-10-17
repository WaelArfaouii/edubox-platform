import { TestBed } from '@angular/core/testing';

import { ManuscriptsService } from './manuscripts.service';

describe('ManuscriptsService', () => {
  let service: ManuscriptsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ManuscriptsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
