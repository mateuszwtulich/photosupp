import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { TranslateService } from '@ngx-translate/core';
import { ServiceService } from 'src/app/servicehandling/services/service.service';
import { IndicatorTo } from 'src/app/servicehandling/to/IndicatorTo';

@Component({
  selector: 'cf-add-indicator',
  templateUrl: './add-indicator.component.html',
  styleUrls: ['./add-indicator.component.scss']
})
export class AddIndicatorComponent implements OnInit {
    public nameControl = new FormControl("", Validators.required);
    public descriptionControl = new FormControl("", Validators.required);
    public nameSecondControl = new FormControl("", Validators.required);
    public descriptionSecondControl = new FormControl("", Validators.required);
    public amountControl = new FormControl("", Validators.required);
    public doublePriceControl = new FormControl("", Validators.required);
    public locale: string;
    public secondLocale: string;
    public localeEn = "en";
    public localePl = "pl";
    public isLocaleEnabled: boolean;
  
    constructor(
      public dialogRef: MatDialogRef<AddIndicatorComponent>,
      private serviceService: ServiceService,
      private translateService: TranslateService
    ){}
  
    ngOnInit(): void {
      this.locale = this.translateService.currentLang;

      if(this.locale == this.localePl){
        this.secondLocale = this.localeEn;
      } else {
        this.secondLocale = this.localePl;
      }
    }

    public changeLocaleCondition(){
      this.isLocaleEnabled = !this.isLocaleEnabled;
    }
  
    addIndicator() {
      if (this.nameControl.valid && this.descriptionControl.valid && this.amountControl.valid && this.doublePriceControl.valid) {

        let indicatorTo: IndicatorTo = {
          name: this.nameControl.value,
          description: this.descriptionControl.value,
          baseAmount: this.amountControl.value,
          doublePrice: this.doublePriceControl.value,
          locale: this.locale
        };

        this.serviceService.createIndicator(indicatorTo).then(() => {

          if(this.isLocaleEnabled){
            let indicatorSecondTo: IndicatorTo = {
              name: this.nameSecondControl.value,
              description: this.descriptionSecondControl.value,
              baseAmount: this.amountControl.value,
              doublePrice: this.doublePriceControl.value,
              locale: this.secondLocale
            };

            this.serviceService.createIndicator(indicatorSecondTo).then(() => {
              this.dialogRef.close();
            })
          } else {
            this.dialogRef.close();
          }
        })          
      }
    }
  
    closeDialog() {
      this.dialogRef.close();
    }
  }
  