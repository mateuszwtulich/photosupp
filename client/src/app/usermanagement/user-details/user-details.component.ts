import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatFormField } from '@angular/material/form-field';
import { LangChangeEvent, TranslateService } from '@ngx-translate/core';
import { Subscription } from 'rxjs';
import { ApplicationPermissions } from '../shared/enum/ApplicationPermissions';
import { AccountEto } from '../shared/to/AccountEto';
import { PermissionEto } from '../shared/to/PermissionEto';
import { RoleEto } from '../shared/to/RoleEto';
import { UserEto } from '../shared/to/UserEto';

const BASIC_PERM: PermissionEto[] = [{
  name: ApplicationPermissions.AUTH_USER,
  description: "Basic permissions"
}]

const ROLE1: RoleEto = {
  name: "User",
  description: "Description of normal user",
  permissions: BASIC_PERM
}

const ACCOUNT2: AccountEto = {
  username: "test2",
  password: "dsf",
  email: "test2@test.com",
  isActivated: true
}

const USER: UserEto = {
  name: "Tom",
  surname: "Willman",
  account: ACCOUNT2,
  role: ROLE1
}

@Component({
  selector: 'cf-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.scss']
})
export class UserDetailsComponent implements OnInit {
  public email1st: FormControl;
  public email2nd: FormControl;
  public password1st: FormControl;
  public password2nd: FormControl;

  public hide: boolean;
  subscritpion: Subscription = new Subscription();
  user = USER;
  @ViewChild('formField1') formField1: MatFormField;
  @ViewChild('formField2') formField2: MatFormField;
  @Input()
  public userControl: FormControl;

  constructor(private translate: TranslateService) {
    this.hide = true;
    this.email1st = new FormControl('', [Validators.required, Validators.email])
    this.email2nd = new FormControl('', [Validators.required, Validators.email])
    this.password1st = new FormControl('', Validators.required)
    this.password2nd = new FormControl('', Validators.required)
    this.userControl = new FormControl(USER);
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
  }

  getErrorMessage1st() {
    if (this.email1st.hasError('required')) {
      return this.translate.instant('registration.email-null');
    }

    return this.email1st.hasError('email') ? this.translate.instant('registration.email-invalid') : '';
  }

  getErrorMessage2nd() {
    if (this.email2nd.hasError('required')) {
      return this.translate.instant('registration.email-null');
    }

    return this.email2nd.hasError('email') ? this.translate.instant('registration.email-invalid') : '';
  }

  ngOnDestroy() {
    this.subscritpion.unsubscribe();
  }

}
