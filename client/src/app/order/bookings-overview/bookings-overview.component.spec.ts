import { Overlay } from '@angular/cdk/overlay';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { RouterTestingModule } from '@angular/router/testing';
import { TranslateService } from '@ngx-translate/core';
import { NgxPermissionsService, NgxPermissionsStore, USE_PERMISSIONS_STORE } from 'ngx-permissions';
import { MockTranslatePipe } from 'src/app/shared/utils/MockTranslatePipe';
import { MockTranslateService } from 'src/app/shared/utils/MockTranslateService';
import { BookingsOverviewComponent } from './bookings-overview.component';


describe('BookingsOverviewComponent', () => {
  let component: BookingsOverviewComponent;
  let fixture: ComponentFixture<BookingsOverviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientModule, MatDialogModule, RouterTestingModule],
      providers: [
        NgxPermissionsService,
        NgxPermissionsStore,
        MatDialog,
        HttpClient,
        MatSnackBar,
        Overlay,
        {
          provide: TranslateService,
          useClass: MockTranslateService
        },
        { provide: USE_PERMISSIONS_STORE, useValue: undefined}
      ],
      declarations: [ BookingsOverviewComponent, MockTranslatePipe ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BookingsOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
