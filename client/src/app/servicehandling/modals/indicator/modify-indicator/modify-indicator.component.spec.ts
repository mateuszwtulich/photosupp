import { Overlay } from '@angular/cdk/overlay';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TranslateService } from '@ngx-translate/core';
import { IndicatorEto } from 'src/app/servicehandling/to/IndicatorEto';
import { MockTranslatePipe } from 'src/app/shared/utils/MockTranslatePipe';
import { MockTranslateService } from 'src/app/shared/utils/MockTranslateService';
import { ModifyIndicatorComponent } from './modify-indicator.component';


describe('ModifyIndicatorComponent', () => {
  let component: ModifyIndicatorComponent;
  let fixture: ComponentFixture<ModifyIndicatorComponent>;
  let dialogRef: MatDialogRef<ModifyIndicatorComponent>;
  let data: IndicatorEto = new IndicatorEto();

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
      declarations: [ ModifyIndicatorComponent, MockTranslatePipe ]
    })
    .compileComponents();
  });


  beforeEach(() => {
    fixture = TestBed.createComponent(ModifyIndicatorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
