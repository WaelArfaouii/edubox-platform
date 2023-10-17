import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AuthorAsideComponent } from './author-aside.component';

describe('AuthorAsideComponent', () => {
  let component: AuthorAsideComponent;
  let fixture: ComponentFixture<AuthorAsideComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AuthorAsideComponent]
    });
    fixture = TestBed.createComponent(AuthorAsideComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
