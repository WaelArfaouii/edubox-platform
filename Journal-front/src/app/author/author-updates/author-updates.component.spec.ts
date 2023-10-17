import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AuthorUpdatesComponent } from './author-updates.component';

describe('AuthorUpdatesComponent', () => {
  let component: AuthorUpdatesComponent;
  let fixture: ComponentFixture<AuthorUpdatesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AuthorUpdatesComponent]
    });
    fixture = TestBed.createComponent(AuthorUpdatesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
