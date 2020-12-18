import { Overlay } from '@angular/cdk/overlay';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TranslateService } from '@ngx-translate/core';
import { PriceIndicatorEto } from 'src/app/core/to/PriceIndicatorEto';
import { MockTranslatePipe } from 'src/app/shared/utils/MockTranslatePipe';
import { MockTranslateService } from 'src/app/shared/utils/MockTranslateService';
import { ModifyPriceIndicatorComponent } from './modify-price-indicator.component';


describe('ModifyPriceIndicatorComponent', () => {
  let component: ModifyPriceIndicatorComponent;
  let fixture: ComponentFixture<ModifyPriceIndicatorComponent>;
  let dialogRef: MatDialogRef<ModifyPriceIndicatorComponent>;
  let data: PriceIndicatorEto[] = [];

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientModule],
      providers: [
        {provide: MatDialogRef, useValue: dialogRef},
        { provide: MAT_DIALOG_DATA, useValue: data },
        HttpClient,
        MatSnackBar,
        Overlay,
        {
          provide: TranslateService,
          useClass: MockTranslateService
        },
      ],
      declarations: [ ModifyPriceIndicatorComponent, MockTranslatePipe ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModifyPriceIndicatorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
