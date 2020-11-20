import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatFormField } from '@angular/material/form-field';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'cf-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  public email: FormControl;
  public hide: boolean;
  @ViewChild('formField') formField: MatFormField;

  constructor(private translate: TranslateService) {
    this.hide = true;
    this.email = new FormControl('', [Validators.required, Validators.email])
   }

  ngOnInit(): void {
  }

  ngAfterViewInit(): void {
    this.formField.hintLabel = this.translate.instant('registration.hint')
  }

  getErrorMessage() {
    if (this.email.hasError('required')) {
      return this.translate.instant('registration.email-null');
    }

    return this.email.hasError('email') ? this.translate.instant('registration.email-invalid') : '';
  }

}
