import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatAccordion } from '@angular/material/expansion';
import { LangChangeEvent, TranslateService } from '@ngx-translate/core';
import { Subscription } from 'rxjs';
import { Combined } from 'src/app/core/to/Combined';
import { IndicatorEto } from 'src/app/core/to/IndicatorEto';
import { PriceIndicatorTo } from 'src/app/core/to/PriceIndicatorTo';
import { ServiceEto } from 'src/app/core/to/ServiceEto';

@Component({
  selector: 'cf-calculate',
  templateUrl: './calculate.component.html',
  styleUrls: ['./calculate.component.scss']
})
export class CalculateComponent implements OnInit {
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;

  fuelIndicatorPL = {
    id: 3,
    name: "Odległość od Częstochowy",
    description: "Proszę podać liczbę kilometrów Państwa lokalizacji od Częstochowy",
    locale: "pl",
    baseAmount: 20,
    doublePrice: 20
  }

  fuelIndicatorEN = {
    id: 4,
    name: "Distance from Czestochowa",
    description: "Kindly provide number of kilometers to your localization from Czestochowa",
    locale: "en",
    baseAmount: 20,
    doublePrice: 20
  }

  fotoIndicators: IndicatorEto[] = [{
    id: 1,
    name: "Szacowna liczba zdjęć",
    description: "Dla foto takiej proponujemy taką liczbę itp",
    locale: "pl",
    baseAmount: 50,
    doublePrice: 200
  },
{
  id: 2,
  name: "Predicted number of photos",
  description: "For this kind of service we propose the number",
  locale: "en",
  baseAmount: 50,
  doublePrice: 200
},
  this.fuelIndicatorPL,
  this.fuelIndicatorEN
  ]

  filmIndicators: IndicatorEto[] = [{
    id: 5,
    name: "Szacowna liczba filmów",
    description: "Dla filmu takiego proponujemy taką liczbę filmów",
    locale: "pl",
    baseAmount: 1,
    doublePrice: 150
  },
{
  id: 6,
  name: "Predicted number of clips",
  description: "For this kind of service we propose the number",
  locale: "en",
  baseAmount: 1,
  doublePrice: 150
},
{
  id: 7,
  name: "Szacowna liczba minut dla filmu",
  description: "Dla filmu takiego typu proponujemy taką liczbę minut",
  locale: "pl",
  baseAmount: 2,
  doublePrice: 40
},
{
  id: 8,
name: "Predicted number of minutes for each clip",
description: "For this kind of service we propose the number",
locale: "en",
baseAmount: 2,
doublePrice: 40
},
  this.fuelIndicatorPL,
  this.fuelIndicatorEN
  ]
  
  services: ServiceEto[] = [];
  servicesStored: ServiceEto[] = [{
    id: 1,
    name: "foto",
    description: "opis",
    locale: "pl",
    basePrice: 300,
    indicators: this.fotoIndicators
  },
{
  id: 2,
  name: "Photo",
  description: "Description",
  locale: "en",
  basePrice: 300,
  indicators: this.fotoIndicators
},
{
  id: 3,
  name: "film",
  description: "opis filmu",
  locale: "pl",
  basePrice: 600,
  indicators: this.filmIndicators
},
{
  id: 4,
name: "Film",
description: "Description",
locale: "en",
basePrice: 600,
indicators: this.filmIndicators
}];


priceIndicators: PriceIndicatorTo[];
combinedList: Combined[] = [];
selectedService: ServiceEto;
subscription: Subscription = new Subscription();
@ViewChild('stepper') stepper;

@ViewChild(MatAccordion) accordion: MatAccordion;


  constructor(private _formBuilder: FormBuilder, private translate: TranslateService) {
  }

  ngOnInit() {
    this.priceIndicators = [];

    this.firstFormGroup = this._formBuilder.group({
      firstCtrl: ['', Validators.required]
    });
    this.secondFormGroup = this._formBuilder.group({
      secondCtrl: ['', Validators.required]
    });

    this.subscription.add(this.translate.onLangChange.subscribe((event: LangChangeEvent) =>{
      this.filterServices(event.lang);
    }));
  }

  ngAfterViewChecked() {
    this.filterServices(this.translate.currentLang);
  
  }

  filterServices(lang: string){
    this.services = [];
    this.servicesStored.forEach(service => {
      if(service.locale == lang){
        service.indicators = service.indicators.filter(indicator => indicator.locale==lang);
        this.services.push(service);
      }
    })
  }

  changeService(currentService: ServiceEto){
    this.firstFormGroup.controls['firstCtrl'].setValue(
      this.services.find(service => currentService.basePrice == service.basePrice && currentService.locale != service.locale));
  }

  setService(expanded: boolean, service: ServiceEto){
    if(expanded == true){
      this.refreshIndicators(service);
      this.firstFormGroup.controls['firstCtrl'].setValue(service);
    } else {
      this.firstFormGroup.controls['firstCtrl'].setValue(null);
      this.priceIndicators = [];
    }
  }

  refreshIndicators(service: ServiceEto){
    this.priceIndicators = [];

    service.indicators.forEach(indicator => this.priceIndicators.push({
      indicatorId: indicator.id,
      bookingId: null,
      price: 0,
      amount: indicator.baseAmount,
    }));
  }

  formatLabel(value: number) {
    if (value >= 10) {
      return Math.round(value / 1000) + 'k';
    }

    return value;
  }

  calculateTheIndicatorCost(i: number, indicator: IndicatorEto): string {
    this.priceIndicators[i].price = ((this.priceIndicators[i].amount / indicator.baseAmount - 1) * indicator.doublePrice)
    return this.priceIndicators[i].price.toFixed();
  }

  calculateThePredictedCost(): number{
    let sum = this.firstFormGroup.controls['firstCtrl'].value.basePrice;
    this.priceIndicators.forEach(priceIndicator => sum += priceIndicator.price);
    return sum;
  }

  ngOnDestroy(){
    this.subscription.unsubscribe();
  }
}
