import { Overlay } from '@angular/cdk/overlay';
import { HttpClientModule } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TranslateService } from '@ngx-translate/core';
import { MockTranslatePipe } from 'src/app/shared/utils/MockTranslatePipe';
import { MockTranslateService } from 'src/app/shared/utils/MockTranslateService';
import { OrderService } from './order.service';


describe('OrderService', () => {
  let service: OrderService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        MatSnackBar,
        Overlay,
        {
          provide: TranslateService,
          useClass: MockTranslateService
        },
      ],
      declarations: [ MockTranslatePipe ],
      imports: [
        HttpClientModule
      ]
    });    service = TestBed.inject(OrderService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
