import { Overlay } from '@angular/cdk/overlay';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TranslateService } from '@ngx-translate/core';
import { AddressEto } from 'src/app/order/shared/to/AddressEto';
import { BookingEto } from 'src/app/order/shared/to/BookingEto';
import { MockTranslatePipe } from 'src/app/shared/utils/MockTranslatePipe';
import { MockTranslateService } from 'src/app/shared/utils/MockTranslateService';
import { ModifyBookingComponent } from './modify-booking.component';

describe('ModifyBookingComponent', () => {
  let component: ModifyBookingComponent;
  let fixture: ComponentFixture<ModifyBookingComponent>;
  let dialogRef: MatDialogRef<ModifyBookingComponent>;
  let data: BookingEto = new BookingEto();
  data.addressEto = new AddressEto();

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
      declarations: [ ModifyBookingComponent, MockTranslatePipe ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModifyBookingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
