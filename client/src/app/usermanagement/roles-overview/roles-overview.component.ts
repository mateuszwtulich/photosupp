import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort, Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { TranslateService } from '@ngx-translate/core';
import { SortUtil } from 'src/app/shared/utils/SortUtil';
import { ApplicationPermissions } from '../shared/enum/ApplicationPermissions';
import { PermissionEto } from '../shared/to/PermissionEto';
import { RoleEto } from '../shared/to/RoleEto';

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

@Component({
  selector: 'cf-roles-overview',
  templateUrl: './roles-overview.component.html',
  styleUrls: ['./roles-overview.component.scss']
})

export class RolesOverviewComponent implements OnInit {
  displayedColumns: string[] = ['name', 'description', 'permissions', 'actions'];
  dataSource = new MatTableDataSource([ROLE1, ROLE2]);
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

  private prepareFilterPredicate(): (data: RoleEto, filter: string) => boolean {
    return (data: RoleEto, filter: string) => {
      let inPermissions: boolean = !!data.permissions
      .find(permission => this.translate.instant("permissions." + permission.name).toLocaleLowerCase().includes(filter));
      console.log(inPermissions);

      return data.name.toLocaleLowerCase().includes(filter) || data.description.toLocaleLowerCase().includes(filter) || inPermissions;
    };
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
        case "description":
          return SortUtil.compare(a.description, b.description, isAsc);
        case "permissions":
          return SortUtil.compare(this.translate.instant("permissions." + a.name), this.translate.instant("permissions." + b.name), isAsc);
        default:
          return 0;
      }
    });
  }
}
