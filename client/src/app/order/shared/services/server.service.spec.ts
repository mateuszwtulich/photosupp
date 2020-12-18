import { HttpClientModule } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TranslateService } from '@ngx-translate/core';
import { MockTranslatePipe } from 'src/app/shared/utils/MockTranslatePipe';
import { MockTranslateService } from 'src/app/shared/utils/MockTranslateService';
import { ServerService } from './server.service';


describe('ServerService', () => {
  let service: ServerService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        MatSnackBar,
        {
          provide: TranslateService,
          useClass: MockTranslateService
        },
      ],
      declarations: [ MockTranslatePipe ],
      imports: [
        HttpClientModule
      ]
    });    service = TestBed.inject(ServerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
