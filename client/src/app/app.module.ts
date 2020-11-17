import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {platformBrowserDynamic} from '@angular/platform-browser-dynamic';
import { SharedModule } from './shared/shared.module';
import { LoginModule } from './login/login.module';
import { CoreModule } from './core/core.module';
import { OrderModule } from './order/order.module';
import { CalendarModule } from './calendar/calendar.module';
import { PricingModule } from './pricing/pricing.module';
import {MatNativeDateModule} from '@angular/material/core';
import { ServicehandlingModule } from './servicehandling/servicehandling.module';
import { UsermanagementModule } from './usermanagement/usermanagement.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {MAT_FORM_FIELD_DEFAULT_OPTIONS} from '@angular/material/form-field';
import {TranslateLoader, TranslateModule} from '@ngx-translate/core';
import {TranslateHttpLoader} from '@ngx-translate/http-loader';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    CommonModule,
    AppRoutingModule,
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    SharedModule,
    LoginModule,
    CoreModule,
    OrderModule,
    CalendarModule,
    PricingModule,
    ServicehandlingModule,
    UsermanagementModule,
    MatNativeDateModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
          useFactory: HttpLoaderFactory, // exported factory function needed for AoT compilation
          deps: [HttpClient]
      }
    })  ],
  bootstrap: [AppComponent],
  providers: [
    { provide: MAT_FORM_FIELD_DEFAULT_OPTIONS, useValue: { appearance: 'fill' } },
  ]
})
export class AppModule { }

platformBrowserDynamic().bootstrapModule(AppModule)
  .catch(err => console.error(err));

  export function HttpLoaderFactory(http: HttpClient) {
    return new TranslateHttpLoader(http);
}
