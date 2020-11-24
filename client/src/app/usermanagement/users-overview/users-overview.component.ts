import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort, Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { TranslateService } from '@ngx-translate/core';
import { SortUtil } from 'src/app/core/utils/SortUtil';
import { ApplicationPermissions } from '../shared/enum/ApplicationPermissions';
import { AccountEto } from '../shared/to/AccountEto';
import { PermissionEto } from '../shared/to/PermissionEto';
import { RoleEto } from '../shared/to/RoleEto';
import { UserEto } from '../shared/to/UserEto';

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
  username: "test1",
  password: "dsf",
  email: "test1@test.com",
  isActivated: false
}

const ACCOUNT2: AccountEto = {
  username: "test2",
  password: "dsf",
  email: "test2@test.com",
  isActivated: true
}

const COORDINATOR: UserEto = {
  name: "John",
  surname: "Smith",
  account: ACCOUNT1,
  role: ROLE2
}

const USER: UserEto = {
  name: "Tom",
  surname: "Willman",
  account: ACCOUNT2,
  role: ROLE1
}

@Component({
  selector: 'cf-users-overview',
  templateUrl: './users-overview.component.html',
  styleUrls: ['./users-overview.component.scss']
})
export class UsersOverviewComponent implements OnInit {
  displayedColumns: string[] = ['name', 'surname', 'username', 'email', 'isActivated', 'role', 'actions'];
  dataSource = new MatTableDataSource([COORDINATOR, USER]);
  isSpinnerDisplayed = false;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private translate: TranslateService) { }

  ngOnInit(): void {
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
    this.dataSource.filterPredicate = this.prepareFilterPredicate();
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  private prepareFilterPredicate(): (data: UserEto, filter: string) => boolean {
    return (data: UserEto, filter: string) => {

      return data.name.toLocaleLowerCase().includes(filter) || data.surname.toLocaleLowerCase().includes(filter) || 
      data.account.username.toLocaleLowerCase().includes(filter) || data.account.email.toLocaleLowerCase().includes(filter) ||
        this.translate.instant("users." + data.account.isActivated).toLocaleLowerCase().includes(filter) || 
        data.role.name.toLocaleLowerCase().includes(filter);
    };
  }

  filterStatus(status: string) {
    if (status != "ALL") {
      this.dataSource.filter = status.trim().toLowerCase();
    } else {
      this.dataSource.filter = "";
    }
  }

  sortData(sort: Sort) {
    const data = this.dataSource.data.slice();
    if (!sort.active || sort.direction === "") {
      this.dataSource.data = data;
    }
    this.dataSource.data = data.sort((a, b) => {
      const isAsc = sort.direction === "asc";
      switch (sort.active) {
        case "name":
          return SortUtil.compare(a.name, b.name, isAsc);
        case "surname":
          return SortUtil.compare(a.surname, b.surname, isAsc);
        case "isActivated":
          return SortUtil.compare(this.translate.instant("users." + a.account.isActivated), this.translate.instant("users." + b.account.isActivated), isAsc);
        case "username":
          return SortUtil.compare(a.account.username, b.account.username, isAsc);
        case "email":
          return SortUtil.compare(a.account.email, b.account.email, isAsc);
        case "role":
          return SortUtil.compare(a.role.name, b.role.name, isAsc);
        default:
          return 0;
      }
    });
  }
}
