import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CalendarBComponent } from './calendar-b.component';

describe('CalendarBComponent', () => {
  let component: CalendarBComponent;
  let fixture: ComponentFixture<CalendarBComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CalendarBComponent]
    });
    fixture = TestBed.createComponent(CalendarBComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
