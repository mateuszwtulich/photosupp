import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ServicehandlingRoutingModule } from './servicehandling-routing.module';
import { ServicePlanningComponent } from './service-planning/service-planning.component';
import { ServicesOverviewComponent } from './services-overview/services-overview.component';
import { IndicatorsOverviewComponent } from './indicators-overview/indicators-overview.component';


@NgModule({
  declarations: [ServicePlanningComponent, ServicesOverviewComponent, IndicatorsOverviewComponent],
  imports: [
    CommonModule,
    ServicehandlingRoutingModule
  ],
  exports: [ServicePlanningComponent, ServicesOverviewComponent, IndicatorsOverviewComponent]
})
export class ServicehandlingModule { }
