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

const BASIC_PERM: PermissionEto[] = [{
  name: ApplicationPermissions.AUTH_USER,
  description: "Basic permissions"
}]

const SUPER_PERM: PermissionEto[] = [{
  name: ApplicationPermissions.A_CRUD_SUPER,
  description: "All permissions"
}]

const ROLE1: RoleEto = {
  name: "User",
  description: "Description of normal user",
  permissions: BASIC_PERM
}

const ROLE2: RoleEto = {
  name: "Manager",
  description: "Description of manager user",
  permissions: SUPER_PERM
}

const ACCOUNT1: AccountEto = {
  id: 1,
  username: "test1",
  password: "dsf",
  email: "test1@test.com",
  isActivated: false
}

const ACCOUNT2: AccountEto = {
  id: 2,
  username: "test2",
  password: "dsf",
  email: "test2@test.com",
  isActivated: true
}

const COORDINATOR: UserEto = {
  id: 1,
  name: "John",
  surname: "Smith",
  account: ACCOUNT1,
  role: ROLE2
}

const USER: UserEto = {
  id: 2,
  name: "Tom",
  surname: "Willman",
  account: ACCOUNT2,
  role: ROLE1
}

@Injectable({
  providedIn: 'root'
})
export class UsersService {
  public subscription = new Subscription();
  public datesDataSource: BehaviorSubject<UserEto> = new BehaviorSubject(null);
  public datesData = this.datesDataSource.asObservable();

  constructor(
    private http: HttpClient,
    ) { }

  public findAllUsers() { }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  public createUser(userTo: UserTo) {
    return this.http.post(UserManagementRestServicePaths.USER_PATH(), userTo).pipe(
      map((userEto: UserEto) => userEto));
  }
}
