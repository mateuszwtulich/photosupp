import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SchedulerComponent } from '../calendar/scheduler/scheduler.component';
import { BookingDetailsComponent } from '../order/booking-details/booking-details.component';
import { BookingsOverviewComponent } from '../order/bookings-overview/bookings-overview.component';
import { OrderDetailsComponent } from '../order/order-details/order-details.component';
import { OrdersOverviewComponent } from '../order/orders-overview/orders-overview.component';
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
    { path: 'home/login', component: LoginComponent },
    { path: 'client/order/planning', component: BookingsPlanningComponent },
    { path: 'client/scheduler', component: SchedulerComponent },
    { path: 'manager/scheduler', component: SchedulerComponent },
    { path: 'manager/orders', component: OrdersOverviewComponent },
    { path: 'client/orders', component: OrdersOverviewComponent },
    { path: 'client/orders/details/:orderNumber', component: OrderDetailsComponent },
    { path: 'manager/orders/details/:orderNumber', component: OrderDetailsComponent },
    { path: 'client/orders/booking/details/:id', component: BookingDetailsComponent },
    { path: 'manager/orders/booking/details/:id', component: BookingDetailsComponent },
    { path: 'manager/services', component: ServicesOverviewComponent },
    { path: 'client/user/details', component: UserDetailsComponent },
    { path: 'manager/user/details', component: UserDetailsComponent },
    { path: 'manager/user/overview', component: UsersOverviewComponent },
    { path: 'manager/role/overview', component: RolesOverviewComponent },
    { path: 'home/calculate', component: CalculateComponent },
    { path: 'home/forgotten-password', component: ForgottenPasswordComponent }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class CoreRoutingModule { }
