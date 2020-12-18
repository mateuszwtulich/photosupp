import { Overlay } from '@angular/cdk/overlay';
import { HttpClient, HttpHandler } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSnackBar } from '@angular/material/snack-bar';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { TranslateService } from '@ngx-translate/core';
import { NgxPermissionsService, NgxPermissionsStore, USE_PERMISSIONS_STORE } from 'ngx-permissions';
import { LocalStorageService } from 'src/app/shared/cache/localStorage.service';
import { MockTranslatePipe } from 'src/app/shared/utils/MockTranslatePipe';
import { MockTranslateService } from 'src/app/shared/utils/MockTranslateService';
import { UserDetailsComponent } from './user-details.component';


describe('UserDetailsComponent', () => {
  let component: UserDetailsComponent;
  let fixture: ComponentFixture<UserDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReactiveFormsModule, FormsModule, MatFormFieldModule, MatDialogModule, MatInputModule, NoopAnimationsModule, RouterTestingModule],
      providers: [
        HttpClient,
        LocalStorageService,
        NgxPermissionsService,
        NgxPermissionsStore,
        HttpHandler,
        MatSnackBar,
        Overlay,
        {
          provide: TranslateService,
          useClass: MockTranslateService
        },
        { provide: USE_PERMISSIONS_STORE, useValue: undefined},
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
      declarations: [ UserDetailsComponent, MockTranslatePipe ]  })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
