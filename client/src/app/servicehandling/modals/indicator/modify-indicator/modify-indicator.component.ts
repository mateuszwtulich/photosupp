import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ServiceService } from 'src/app/servicehandling/services/service.service';
import { IndicatorEto } from 'src/app/servicehandling/to/IndicatorEto';
import { IndicatorTo } from 'src/app/servicehandling/to/IndicatorTo';
import { AddIndicatorComponent } from '../add-indicator/add-indicator.component';

@Component({
  selector: 'cf-modify-indicator',
  templateUrl: './modify-indicator.component.html',
  styleUrls: ['./modify-indicator.component.scss']
})
export class ModifyIndicatorComponent implements OnInit {
  public nameControl: FormControl;
  public descriptionControl: FormControl;
  public amountControl: FormControl;
  public doublePriceControl: FormControl;


  constructor(
    public dialogRef: MatDialogRef<AddIndicatorComponent>,
    private serviceService: ServiceService,
    @Inject(MAT_DIALOG_DATA) public data: IndicatorEto
  ){}

  ngOnInit(): void {
    this.nameControl = new FormControl(this.data.name, Validators.required);
    this.descriptionControl = new FormControl(this.data.description, Validators.required);
    this.amountControl = new FormControl(this.data.baseAmount, Validators.required);
    this.doublePriceControl = new FormControl(this.data.doublePrice, Validators.required);
  }

  modifyIndicator() {
    if (this.nameControl.valid && this.descriptionControl.valid && this.amountControl.valid && this.doublePriceControl.valid) {

      let indicatorTo: IndicatorTo = {
        name: this.nameControl.value,
        description: this.descriptionControl.value,
        baseAmount: this.amountControl.value,
        doublePrice: this.doublePriceControl.value,
        locale: this.data.locale
      };

      this.serviceService.modifyIndicator(indicatorTo, this.data.id).then(() => {
          this.dialogRef.close();
        })
      }         
    }

  closeDialog() {
    this.dialogRef.close();
  }
}
