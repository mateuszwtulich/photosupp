import { TestBed } from '@angular/core/testing';
import { TranslateFakeLoader, TranslateModule } from '@ngx-translate/core';
import { SchedulerService } from './scheduler.service';


describe('SchedulerService', () => {
  let service: SchedulerService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        TranslateModule.forRoot({
          loader: { provide: TranslateModule, useClass: TranslateFakeLoader }
      })]});
    service = TestBed.inject(SchedulerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
