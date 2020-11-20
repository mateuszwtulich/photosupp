import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SchedulerComponent } from '../calendar/scheduler/scheduler.component';
import { BookingsOverviewComponent } from '../order/bookings-overview/bookings-overview.component';
import { DetailsComponent } from '../order/details/details.component';
import { BookingsPlanningComponent } from '../pricing/bookings-planning/bookings-planning.component';
import { CalculateComponent } from '../pricing/calculate/calculate.component';
import { IndicatorsOverviewComponent } from '../servicehandling/indicators-overview/indicators-overview.component';
import { ServicesOverviewComponent } from '../servicehandling/services-overview/services-overview.component';
import { RolesOverviewComponent } from '../usermanagement/roles-overview/roles-overview.component';
import { UserDetailsComponent } from '../usermanagement/user-details/user-details.component';
import { UsersOverviewComponent } from '../usermanagement/users-overview/users-overview.component';
import { ClientPanelComponent } from './client-panel/client-panel.component';
import { ForgottenPasswordComponent } from './forgotten-password/forgotten-password.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { ManagerPanelComponent } from './manager-panel/manager-panel.component';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' }, 
  { path: 'home', component: HomeComponent },
  { path: 'manager', component: ManagerPanelComponent },
  { path: 'client', component: ClientPanelComponent },
  { path: 'login', component: LoginComponent },
  { path: 'order/planning', component: BookingsPlanningComponent },
  { path: 'scheduler', component: SchedulerComponent },
  { path: 'order/overview', component: BookingsOverviewComponent },
  { path: 'order/details', component: DetailsComponent },
  { path: 'manager/service', component: ServicesOverviewComponent },
  { path: 'manager/indicator', component: IndicatorsOverviewComponent },
  { path: 'user/details', component: UserDetailsComponent},
  { path: 'user/overview', component: UsersOverviewComponent},
  { path: 'role/overview', component: RolesOverviewComponent},
  { path: 'calculate', component: CalculateComponent},
  { path: 'forgotten-password', component: ForgottenPasswordComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CoreRoutingModule { }
