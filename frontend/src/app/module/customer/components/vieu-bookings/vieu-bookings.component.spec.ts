import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VieuBookingsComponent } from './vieu-bookings.component';

describe('VieuBookingsComponent', () => {
  let component: VieuBookingsComponent;
  let fixture: ComponentFixture<VieuBookingsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [VieuBookingsComponent]
    });
    fixture = TestBed.createComponent(VieuBookingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
