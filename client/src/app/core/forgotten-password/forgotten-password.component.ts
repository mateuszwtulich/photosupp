import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'cf-forgotten-password',
  templateUrl: './forgotten-password.component.html',
  styleUrls: ['./forgotten-password.component.scss']
})
export class ForgottenPasswordComponent implements OnInit {
  public email: FormControl;

  constructor(private translate: TranslateService) {     
    this.email = new FormControl('', [Validators.required, Validators.email])
   }

  ngOnInit(): void {
  }

  getErrorMessage() {
    if (this.email.hasError('required')) {
      return this.translate.instant('registration.email-null');
    }

    return this.email.hasError('email') ? this.translate.instant('registration.email-invalid') : '';
  }
}
