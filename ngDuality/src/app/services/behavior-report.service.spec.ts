import { TestBed } from '@angular/core/testing';

import { BehaviorReportService } from './behavior-report.service';

describe('BehaviorReportService', () => {
  let service: BehaviorReportService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BehaviorReportService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
