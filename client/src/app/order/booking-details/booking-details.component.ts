import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { ApplicationPermissions } from 'src/app/usermanagement/shared/enum/ApplicationPermissions';
import { AccountEto } from 'src/app/usermanagement/shared/to/AccountEto';
import { PermissionEto } from 'src/app/usermanagement/shared/to/PermissionEto';
import { RoleEto } from 'src/app/usermanagement/shared/to/RoleEto';
import { UserEto } from 'src/app/usermanagement/shared/to/UserEto';

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
  selector: 'cf-booking-details',
  templateUrl: './booking-details.component.html',
  styleUrls: ['./booking-details.component.scss']
})
export class BookingDetailsComponent implements OnInit {
  public userControl: FormControl;
  user = USER;

  constructor() { }

  ngOnInit(): void {
    this.userControl = new FormControl(USER);
  }

}
