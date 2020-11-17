import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UsermanagementRoutingModule } from './usermanagement-routing.module';
import { RolesOverviewComponent } from './roles-overview/roles-overview.component';
import { UsersOverviewComponent } from './users-overview/users-overview.component';
import { UserDetailsComponent } from './user-details/user-details.component';


@NgModule({
  declarations: [RolesOverviewComponent, UsersOverviewComponent, UserDetailsComponent],
  imports: [
    CommonModule,
    UsermanagementRoutingModule
  ],
  exports: [RolesOverviewComponent, UsersOverviewComponent, UserDetailsComponent]
})
export class UsermanagementModule { }
