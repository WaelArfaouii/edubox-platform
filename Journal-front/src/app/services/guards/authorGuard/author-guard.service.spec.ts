import { TestBed } from '@angular/core/testing';

import { AuthorGuardService } from './author-guard.service';

describe('AuthorGuardService', () => {
  let service: AuthorGuardService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuthorGuardService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
