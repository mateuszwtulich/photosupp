import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatAccordion } from '@angular/material/expansion';
import { TranslateService, LangChangeEvent } from '@ngx-translate/core';
import { Subscription } from 'rxjs';
import { SchedulerService } from 'src/app/calendar/services/scheduler.service';
import { Combined } from 'src/app/core/to/Combined';
import { PriceIndicatorEto } from 'src/app/core/to/PriceIndicatorEto';
import { PriceIndicatorTo } from 'src/app/core/to/PriceIndicatorTo';
import { BookingEto } from 'src/app/order/shared/to/BookingEto';
import { IndicatorEto } from 'src/app/servicehandling/to/IndicatorEto';
import { ServiceEto } from 'src/app/servicehandling/to/ServiceEto';
import { AccountEto } from 'src/app/usermanagement/shared/to/AccountEto';
import { RoleEto } from 'src/app/usermanagement/shared/to/RoleEto';
import { UserEto } from 'src/app/usermanagement/shared/to/UserEto';

const ROLE1: RoleEto = {
  name: "User",
  description: "Description of normal user",
  permissions: null
}

const ACCOUNT2: AccountEto = {
  id: 2,
  username: "test2",
  password: "dsf",
  email: "test2@test.com",
  isActivated: true
}

const USER: UserEto = {
  id: 1,
  name: "Tom",
  surname: "Willman",
  account: ACCOUNT2,
  role: ROLE1
}

@Component({
  selector: 'cf-bookings-planning',
  templateUrl: './bookings-planning.component.html',
  styleUrls: ['./bookings-planning.component.scss']
})
export class BookingsPlanningComponent implements OnInit {
  dateFormGroup: FormGroup;
  firstFormGroup: FormGroup;
  addressFormGroup: FormGroup;
  booking: BookingEto;

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
    indicatorEtoList: this.fotoIndicators
  },
  {
    id: 2,
    name: "Photo",
    description: "Description",
    locale: "en",
    basePrice: 300,
    indicatorEtoList: this.fotoIndicators
  },
  {
    id: 3,
    name: "film",
    description: "opis filmu",
    locale: "pl",
    basePrice: 600,
    indicatorEtoList: this.filmIndicators
  },
  {
    id: 4,
    name: "Film",
    description: "Description",
    locale: "en",
    basePrice: 600,
    indicatorEtoList: this.filmIndicators
  }];

  bookingControl: FormControl;
  priceIndicators: PriceIndicatorEto[];
  combinedList: Combined[] = [];
  selectedService: ServiceEto;
  subscription: Subscription = new Subscription();
  @ViewChild('stepper') stepper;

  @ViewChild(MatAccordion) accordion: MatAccordion;


  constructor(private _formBuilder: FormBuilder, private translate: TranslateService,
     private schedulerService: SchedulerService, private elementRef: ElementRef
     ) {
  }

  ngOnInit() {
    this.priceIndicators = [];

    this.dateFormGroup = this._formBuilder.group({
      dateCtrl: ['', Validators.required]
    });
    this.firstFormGroup = this._formBuilder.group({
      firstCtrl: ['', Validators.required]
    });
    this.addressFormGroup = this._formBuilder.group({
      cityCtrl: ['', Validators.required],
      streetCtrl: ['', Validators.required],
      buildingNumberCtrl: ['', Validators.required],
      apartmentNumberCtrl: [''],
      postalCodeCtrl: ['', Validators.required],
      nameCtrl: ['', Validators.required],
      descriptionCtrl: ['']
    });


    this.subscription.add(this.translate.onLangChange.subscribe((event: LangChangeEvent) => {
      this.filterServices(event.lang);
    }));
    this.filterServices(this.translate.currentLang);

    this.subscription.add(this.schedulerService.datesData.subscribe((booking: BookingEto) => {
      this.dateFormGroup.controls['dateCtrl'].setValue(booking);
    }));

    this.bookingControl = new FormControl('', Validators.required);
  }

  ngAfterViewInit() {
    this.elementRef.nativeElement.querySelectorAll('mat-step-header').forEach(item => {
      item.addEventListener('click', event => {
        if(event.currentTarget.ariaPosInSet - 1 == 4 && this.addressFormGroup.valid){
          this.goToLastStep();
        }
       });
    });
  }

  filterServices(lang: string) {
    this.services = [];
    this.servicesStored.forEach(service => {
      if (service.locale == lang) {
        service.indicatorEtoList = service.indicatorEtoList.filter(indicator => indicator.locale == lang);
        this.services.push(service);
      }
    })
  }

  changeService(currentService: ServiceEto) {
    this.firstFormGroup.controls['firstCtrl'].setValue(
      this.services.find(service => currentService.basePrice == service.basePrice && currentService.locale != service.locale));
  }

  setService(expanded: boolean, service: ServiceEto) {
    if (expanded == true) {
      this.refreshIndicators(service);
      this.firstFormGroup.controls['firstCtrl'].setValue(service);
    } else {
      this.firstFormGroup.controls['firstCtrl'].setValue(null);
      this.priceIndicators = [];
    }
  }

  refreshIndicators(service: ServiceEto) {
    this.priceIndicators = [];

    service.indicatorEtoList.forEach(indicator => this.priceIndicators.push({
      indicator: indicator,
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

  calculateThePredictedCost(): number {
    let sum = this.firstFormGroup.controls['firstCtrl'].value.basePrice;
    this.priceIndicators.forEach(priceIndicator => sum += priceIndicator.price);
    return sum;
  }

  goToLastStep(){
    this.booking = {
      id: null,
      name: this.addressFormGroup.controls['nameCtrl'].value,
      description: this.addressFormGroup.controls['descriptionCtrl'].value,
      address: {
        id: 1,
        city: this.addressFormGroup.controls['cityCtrl'].value,
        street: this.addressFormGroup.controls['nameCtrl'].value,
        buildingNumber: this.addressFormGroup.controls['buildingNumberCtrl'].value,
        apartmentNumber: this.addressFormGroup.controls['apartmentNumberCtrl'].value,
        postalCode: this.addressFormGroup.controls['postalCodeCtrl'].value
      },
      service: this.firstFormGroup.controls['firstCtrl'].value,
      user: USER,
      isConfirmed: false,
      predictedPrice: this.calculateThePredictedCost(),
      start: this.dateFormGroup.controls['dateCtrl'].value.start,
      end: this.dateFormGroup.controls['dateCtrl'].value.end,
      modificationDate: null,
      priceIndicatorList: this.priceIndicators
    }

    this.bookingControl.setValue(this.booking);
  }

  ngOnDestroy() {
    this.schedulerService.datesDataSource.next(null);
    this.subscription.unsubscribe();
  }
}
