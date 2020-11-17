import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { OrderRoutingModule } from './order-routing.module';
import { BookingsOverviewComponent } from './bookings-overview/bookings-overview.component';
import { DetailsComponent } from './details/details.component';
import { OrdersOverviewComponent } from './orders-overview/orders-overview.component';


@NgModule({
  declarations: [BookingsOverviewComponent, DetailsComponent, OrdersOverviewComponent],
  imports: [
    CommonModule,
    OrderRoutingModule
  ],
  exports: [BookingsOverviewComponent, DetailsComponent, OrdersOverviewComponent]
})
export class OrderModule { }
