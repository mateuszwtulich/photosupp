import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SchedulerComponent } from '../calendar/scheduler/scheduler.component';
import { LoginPageComponent } from '../login/login-page/login-page.component';
import { BookingsOverviewComponent } from '../order/bookings-overview/bookings-overview.component';
import { DetailsComponent } from '../order/details/details.component';
import { BookingsPlanningComponent } from '../pricing/bookings-planning/bookings-planning.component';
import { IndicatorsOverviewComponent } from '../servicehandling/indicators-overview/indicators-overview.component';
import { ServicePlanningComponent } from '../servicehandling/service-planning/service-planning.component';
import { ServicesOverviewComponent } from '../servicehandling/services-overview/services-overview.component';
import { RolesOverviewComponent } from '../usermanagement/roles-overview/roles-overview.component';
import { UserDetailsComponent } from '../usermanagement/user-details/user-details.component';
import { UsersOverviewComponent } from '../usermanagement/users-overview/users-overview.component';
import { ClientPanelComponent } from './client-panel/client-panel.component';
import { HomeComponent } from './home/home.component';
import { ManagerPanelComponent } from './manager-panel/manager-panel.component';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' }, 
  { path: 'home', component: HomeComponent },
  { path: 'manager', component: ManagerPanelComponent },
  { path: 'client', component: ClientPanelComponent },
  { path: 'login', component: LoginPageComponent },
  { path: 'order/planning', component: BookingsPlanningComponent },
  { path: 'scheduler', component: SchedulerComponent },
  { path: 'order/overview', component: BookingsOverviewComponent },
  { path: 'order/details', component: DetailsComponent },
  { path: 'manager/service', component: ServicesOverviewComponent },
  { path: 'manager/indicator', component: IndicatorsOverviewComponent },
  { path: 'user/details', component: UserDetailsComponent},
  { path: 'user/overview', component: UsersOverviewComponent},
  { path: 'role/overview', component: RolesOverviewComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CoreRoutingModule { }
