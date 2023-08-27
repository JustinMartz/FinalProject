import { TestBed } from '@angular/core/testing';

import { BehaviorReportRemarkServiceService } from './behavior-report-remark-service.service';

describe('BehaviorReportRemarkServiceService', () => {
  let service: BehaviorReportRemarkServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BehaviorReportRemarkServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
