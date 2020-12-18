import { Overlay } from '@angular/cdk/overlay';
import { HttpClient, HttpHandler } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { RouterTestingModule } from '@angular/router/testing';
import { TranslateService } from '@ngx-translate/core';
import { LocalStorageService } from 'src/app/shared/cache/localStorage.service';
import { MockLocalStorageService } from 'src/app/shared/utils/MockLocalStorageService';
import { MockTranslatePipe } from 'src/app/shared/utils/MockTranslatePipe';
import { MockTranslateService } from 'src/app/shared/utils/MockTranslateService';
import { BookingsPlanningComponent } from './bookings-planning.component';


describe('BookingsPlanningComponent', () => {
  let component: BookingsPlanningComponent;
  let fixture: ComponentFixture<BookingsPlanningComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReactiveFormsModule, FormsModule, RouterTestingModule, NoopAnimationsModule],
      providers: [
        HttpClient,
        HttpHandler,
        MatSnackBar,
        Overlay,
        {
          provide: LocalStorageService,
          useClass: MockLocalStorageService
        },
        {
          provide: TranslateService,
          useClass: MockTranslateService
          }
      ],
      declarations: [ BookingsPlanningComponent, MockTranslatePipe ]
    })
    .compileComponents();
  });
  

  beforeEach(() => {
    fixture = TestBed.createComponent(BookingsPlanningComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
