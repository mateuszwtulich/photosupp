import { Overlay } from '@angular/cdk/overlay';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TranslateService } from '@ngx-translate/core';
import { MockTranslatePipe } from 'src/app/shared/utils/MockTranslatePipe';
import { MockTranslateService } from 'src/app/shared/utils/MockTranslateService';
import { AccountEto } from 'src/app/usermanagement/shared/to/AccountEto';
import { UserEto } from 'src/app/usermanagement/shared/to/UserEto';
import { ModifyUserComponent } from './modify-user.component';


describe('ModifyUserComponent', () => {
  let component: ModifyUserComponent;
  let fixture: ComponentFixture<ModifyUserComponent>;
  let dialogRef: MatDialogRef<ModifyUserComponent>;
  let data: UserEto = new UserEto();
  data.accountEto = new AccountEto();

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
      declarations: [ ModifyUserComponent, MockTranslatePipe ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModifyUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
