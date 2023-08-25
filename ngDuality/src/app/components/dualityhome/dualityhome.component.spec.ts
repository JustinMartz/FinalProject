import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DualityhomeComponent } from './dualityhome.component';

describe('DualityhomeComponent', () => {
  let component: DualityhomeComponent;
  let fixture: ComponentFixture<DualityhomeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DualityhomeComponent]
    });
    fixture = TestBed.createComponent(DualityhomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
