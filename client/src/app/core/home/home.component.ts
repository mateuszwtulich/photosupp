import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';
import { MatFormField } from '@angular/material/form-field';
import { MatSnackBar } from '@angular/material/snack-bar';
import { LangChangeEvent, TranslateService } from '@ngx-translate/core';
import { Subscription } from 'rxjs';
import { UsersService } from 'src/app/usermanagement/shared/services/users.service';
import { ApplicationPermission, BasicRole } from '../utils/ApplicationPermission';

@Component({
  selector: 'cf-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  public emailFormControl: FormControl;
  public nameFormControl: FormControl;
  public surnameFormControl: FormControl;
  public passwordForm: FormGroup;

  public hide: boolean;
  subscritpion: Subscription = new Subscription();
  public matcher: MyErrorStateMatcher;
  @ViewChild('formField1') formField1: MatFormField;
  @ViewChild('formField2') formField2: MatFormField;

  constructor(
    private _formBuilder: FormBuilder,
    private translate: TranslateService,
    private userService: UsersService,
    private snackbar: MatSnackBar
  ) {

    this.hide = true;
    this.nameFormControl = new FormControl('', Validators.required);
    this.surnameFormControl = new FormControl('', Validators.required);
    this.emailFormControl = new FormControl('', [Validators.required, Validators.email]);

    this.passwordForm = this._formBuilder.group({
      password: ['', [Validators.required]],
      confirmPassword: ['']
    }, { validator: this.checkPasswords })

    this.matcher = new MyErrorStateMatcher();
  }

  ngOnInit(): void {
    this.subscritpion.add(this.translate.onLangChange.subscribe((event: LangChangeEvent) => {
      this.changeHintLabel();
    }));
  }

  ngAfterViewInit(): void {
    this.changeHintLabel();
  }

  private changeHintLabel() {
    this.formField1.hintLabel = this.translate.instant('registration.hint');
    this.formField2.hintLabel = this.translate.instant('registration.hint')
  }

  getErrorMessage() {
    if (this.emailFormControl.hasError('required')) {
      return this.translate.instant('registration.email-null');
    }

    return this.emailFormControl.hasError('email') ? this.translate.instant('registration.email-invalid') : '';
  }

  ngOnDestroy() {
    this.subscritpion.unsubscribe();
  }

  checkPasswords(group: FormGroup) { // here we have the 'passwords' group
    let pass = group.get('password').value;
    let confirmPass = group.get('confirmPassword').value;

    return pass === confirmPass ? null : { notSame: true }
  }

  register() {
    if (this.nameFormControl.valid && this.surnameFormControl.valid && this.passwordForm.valid && this.emailFormControl.valid) {
      let userTo = {
        name: this.nameFormControl.value,
        surname: this.surnameFormControl.value,
        accountTo: {
          password: this.passwordForm.get('password').value,
          email: this.emailFormControl.value,
        },
        roleId: BasicRole.getClientRoleId()
      }

      console.log(userTo);

      this.subscritpion.add(this.userService.createUser(userTo).subscribe((userEto) => {
        this.snackbar.open(this.translate.instant('registration.check-mailbox'));
      },
        (e) => {
          this.snackbar.open(this.translate.instant('registration.error' + " " + e.error.message));
        }))
    }
  }
}

export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const invalidCtrl = !!(control && control.invalid && control.parent.dirty);
    const invalidParent = !!(control && control.parent && control.parent.invalid && control.parent.dirty);

    return (invalidCtrl || invalidParent);
  }
}