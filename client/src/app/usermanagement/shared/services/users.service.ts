import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Subscription } from 'rxjs';
import { ApplicationPermissions } from '../enum/ApplicationPermissions';
import { UserManagementRestServicePaths } from '../rest-service-paths/UserManagementRestServicePaths';
import { AccountEto } from '../to/AccountEto';
import { PermissionEto } from '../to/PermissionEto';
import { RoleEto } from '../to/RoleEto';
import { UserEto } from '../to/UserEto';
import { UserTo } from '../to/UserTo';
import { map } from 'rxjs/operators';
import { MatSnackBar } from '@angular/material/snack-bar';
import { LocalStorageService } from 'src/app/shared/cache/localStorage.service';
import { TranslateService } from '@ngx-translate/core';
import { AccountTo } from '../to/AccountTo';

@Injectable({
  providedIn: 'root'
})
export class UsersService {
  public subscription = new Subscription();
  private loggedUserDataSource: BehaviorSubject<UserEto> = new BehaviorSubject(null);
  public loggedUserData = this.loggedUserDataSource.asObservable();
  private spinnerDataSource: BehaviorSubject<boolean> = new BehaviorSubject(false);
  public spinnerData = this.spinnerDataSource.asObservable();

  constructor(
    private http: HttpClient,
    private snackBar: MatSnackBar,
    private localStorage: LocalStorageService,
    private translate: TranslateService
    ) { }

  public findAllUsers() { }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  public createUser(userTo: UserTo) {
    return this.http.post(UserManagementRestServicePaths.USER_PATH(), userTo).pipe(
      map((userEto: UserEto) => userEto));
  }

  public modifyUser(userTo: UserTo, userId: number){
    return new Promise((resolve, reject) => {
      this.spinnerDataSource.next(true);
      this.subscription.add(this.http.put<UserEto>(`${UserManagementRestServicePaths.USER_PATH()}/${userId}`, userTo).subscribe(
      (user: UserEto) => {
        this.loggedUserDataSource.next(user);
        this.snackBar.open(this.translate.instant('users.modified'))
        this.spinnerDataSource.next(false);
        resolve(user);
      },
      (e) => {
        this.snackBar.open(this.translate.instant('server.error'))
        this.spinnerDataSource.next(false);
        reject();
      }))
    })
  }

  public modifyAccount(userTo: UserTo, userId: number){
    return new Promise((resolve, reject) => {
      this.spinnerDataSource.next(true);
      this.subscription.add(this.http.put<AccountEto>(`${UserManagementRestServicePaths.ACCOUNT_PATH_WITH_ID(userId)}`, userTo.accountTo).subscribe(
      (accountEto: AccountEto) => {
        this.loggedUserDataSource.value.accountEto = accountEto;
        this.snackBar.open(this.translate.instant('users.check-mailbox'))
        this.spinnerDataSource.next(false);
        resolve(accountEto);
      },
      (e) => {
        this.snackBar.open(this.translate.instant('server.error'));
        this.spinnerDataSource.next(false);
        reject();
      }))
    })
  }

  public changePassword(accountTo: AccountTo){
    return new Promise((resolve, reject) => {
      this.spinnerDataSource.next(true);
      this.subscription.add(this.http.put<void>(`${UserManagementRestServicePaths.ACCOUNT_PATH()}`, accountTo).subscribe(
      () => {
        this.snackBar.open(this.translate.instant('users.check-mailbox'))
        this.spinnerDataSource.next(false);
        resolve();
      },
      (e) => {
        this.snackBar.open(this.translate.instant('server.error'));
        this.spinnerDataSource.next(false);
        reject();
      }))
    })
  }

  public getLoggedUser() {
    return new Promise((resolve, reject) => {
      this.spinnerDataSource.next(true);
      let userId = this.localStorage.getUserId();
    this.subscription.add(this.http.get<UserEto>(`${UserManagementRestServicePaths.USER_PATH()}/${userId}`).subscribe(
      (user: UserEto) => {
        this.loggedUserDataSource.next(user);
        this.spinnerDataSource.next(false);
        resolve(user);
      },
      (e) => {
        this.spinnerDataSource.next(false);
        this.snackBar.open(this.translate.instant('server.error'))
        reject();
      }))
    })
  }
}
