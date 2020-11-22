import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatFormField } from '@angular/material/form-field';
import { LangChangeEvent, TranslateService } from '@ngx-translate/core';
import { Subscription } from 'rxjs';

@Component({
  selector: 'cf-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  public email: FormControl;
  public hide: boolean;
  subscritpion: Subscription = new Subscription();
  @ViewChild('formField1') formField1: MatFormField;
  @ViewChild('formField2') formField2: MatFormField;

  constructor(private translate: TranslateService) {
    this.hide = true;
    this.email = new FormControl('', [Validators.required, Validators.email])
   }

  ngOnInit(): void {
    this.subscritpion.add(this.translate.onLangChange.subscribe((event: LangChangeEvent) => {
      this.changeHintLabel();
    }));
  }

  ngAfterViewInit(): void {
    this.changeHintLabel();
  }

  private changeHintLabel(){
    this.formField1.hintLabel = this.translate.instant('registration.hint');
    this.formField2.hintLabel = this.translate.instant('registration.hint')
  }

  getErrorMessage() {
    if (this.email.hasError('required')) {
      return this.translate.instant('registration.email-null');
    }

    return this.email.hasError('email') ? this.translate.instant('registration.email-invalid') : '';
  }

  ngOnDestroy() {
    this.subscritpion.unsubscribe();
  }
}
