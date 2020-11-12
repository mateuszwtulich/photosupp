import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SharedModule } from './shared/shared.module';
import { LoginModule } from './login/login.module';
import { CoreModule } from './core/core.module';
import { OrderModule } from './order/order.module';
import { CalendarModule } from './calendar/calendar.module';
import { PricingModule } from './pricing/pricing.module';
import { ServicehandlingModule } from './servicehandling/servicehandling.module';
import { UsermanagementModule } from './usermanagement/usermanagement.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    SharedModule,
    LoginModule,
    CoreModule,
    OrderModule,
    CalendarModule,
    PricingModule,
    ServicehandlingModule,
    UsermanagementModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
