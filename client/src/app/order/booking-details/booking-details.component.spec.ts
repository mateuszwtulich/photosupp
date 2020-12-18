import { Overlay } from '@angular/cdk/overlay';
import { HttpClient, HttpHandler } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { TranslateService } from '@ngx-translate/core';
import { LocalStorageService } from 'src/app/shared/cache/localStorage.service';
import { MockTranslatePipe } from 'src/app/shared/utils/MockTranslatePipe';
import { MockTranslateService } from 'src/app/shared/utils/MockTranslateService';
import { BookingDetailsComponent } from './booking-details.component';


describe('BookingDetailsComponent', () => {
  let component: BookingDetailsComponent;
  let fixture: ComponentFixture<BookingDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReactiveFormsModule, FormsModule, MatFormFieldModule, MatDialogModule, RouterTestingModule],
      providers: [
        HttpClient,
        LocalStorageService,
        HttpHandler,
        MatSnackBar,
        Overlay,
        {
          provide: TranslateService,
          useClass: MockTranslateService
        },
        {
          provide: ActivatedRoute,
          useValue: {
              snapshot: {
                  paramMap: {
                      get(): string {
                          return '123';
                      },
                  },
              },
          },
      },
      ],
      declarations: [ BookingDetailsComponent, MockTranslatePipe ]    })  
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BookingDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
