import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllManuscriptsComponent } from './all-manuscripts.component';

describe('AllManuscriptsComponent', () => {
  let component: AllManuscriptsComponent;
  let fixture: ComponentFixture<AllManuscriptsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AllManuscriptsComponent]
    });
    fixture = TestBed.createComponent(AllManuscriptsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
