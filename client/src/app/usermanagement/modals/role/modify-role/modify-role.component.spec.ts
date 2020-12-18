import { Overlay } from '@angular/cdk/overlay';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TranslateService } from '@ngx-translate/core';
import { MockTranslatePipe } from 'src/app/shared/utils/MockTranslatePipe';
import { MockTranslateService } from 'src/app/shared/utils/MockTranslateService';
import { RoleEto } from 'src/app/usermanagement/shared/to/RoleEto';
import { ModifyRoleComponent } from './modify-role.component';


describe('ModifyRoleComponent', () => {
  let component: ModifyRoleComponent;
  let fixture: ComponentFixture<ModifyRoleComponent>;
  let dialogRef: MatDialogRef<ModifyRoleComponent>;
  let data: RoleEto = new RoleEto();

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
      declarations: [ ModifyRoleComponent, MockTranslatePipe ]
    })
    .compileComponents();
  });
  
  beforeEach(() => {
    fixture = TestBed.createComponent(ModifyRoleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
