import { Overlay } from '@angular/cdk/overlay';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TranslateService } from '@ngx-translate/core';
import { NgxPermissionsService, NgxPermissionsStore } from 'ngx-permissions';
import { MockTranslatePipe } from 'src/app/shared/utils/MockTranslatePipe';
import { MockTranslateService } from 'src/app/shared/utils/MockTranslateService';
import { ClientPanelComponent } from './client-panel.component';


describe('ClientPanelComponent', () => {
  let component: ClientPanelComponent;
  let fixture: ComponentFixture<ClientPanelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      providers: [
        NgxPermissionsService,
        NgxPermissionsStore,
        MatSnackBar,
        Overlay,
        {
          provide: TranslateService,
          useClass: MockTranslateService
        },
      ],
      declarations: [ ClientPanelComponent, MockTranslatePipe ]    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
