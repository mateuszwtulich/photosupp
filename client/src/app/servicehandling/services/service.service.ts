import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Subscription } from 'rxjs';
import { ServiceHandlingRestServicePaths } from '../rest-service-paths/ServiceHandlingRestServicePaths';
import { ServiceEto } from '../to/ServiceEto';
import { map } from 'rxjs/operators';
import { MatSnackBar } from '@angular/material/snack-bar';

//   fuelIndicatorPL = {
//     id: 3,
//     name: "Odległość od Częstochowy",
//     description: "Proszę podać liczbę kilometrów Państwa lokalizacji od Częstochowy",
//     locale: "pl",
//     baseAmount: 20,
//     doublePrice: 20
//   }

//   fuelIndicatorEN = {
//     id: 4,
//     name: "Distance from Czestochowa",
//     description: "Kindly provide number of kilometers to your localization from Czestochowa",
//     locale: "en",
//     baseAmount: 20,
//     doublePrice: 20
//   }

//   fotoIndicators: IndicatorEto[] = [{
//     id: 1,
//     name: "Szacowna liczba zdjęć",
//     description: "Dla foto takiej proponujemy taką liczbę itp",
//     locale: "pl",
//     baseAmount: 50,
//     doublePrice: 200
//   },
// {
//   id: 2,
//   name: "Predicted number of photos",
//   description: "For this kind of service we propose the number",
//   locale: "en",
//   baseAmount: 50,
//   doublePrice: 200
// },
//   this.fuelIndicatorPL,
//   this.fuelIndicatorEN
//   ]

//   filmIndicators: IndicatorEto[] = [{
//     id: 5,
//     name: "Szacowna liczba filmów",
//     description: "Dla filmu takiego proponujemy taką liczbę filmów",
//     locale: "pl",
//     baseAmount: 1,
//     doublePrice: 150
//   },
// {
//   id: 6,
//   name: "Predicted number of clips",
//   description: "For this kind of service we propose the number",
//   locale: "en",
//   baseAmount: 1,
//   doublePrice: 150
// },
// {
//   id: 7,
//   name: "Szacowna liczba minut dla filmu",
//   description: "Dla filmu takiego typu proponujemy taką liczbę minut",
//   locale: "pl",
//   baseAmount: 2,
//   doublePrice: 40
// },
// {
//   id: 8,
// name: "Predicted number of minutes for each clip",
// description: "For this kind of service we propose the number",
// locale: "en",
// baseAmount: 2,
// doublePrice: 40
// },
//   this.fuelIndicatorPL,
//   this.fuelIndicatorEN
//   ]
  
//   services: ServiceEto[] = [];
//   servicesStored: ServiceEto[] = [{
//     id: 1,
//     name: "foto",
//     description: "opis",
//     locale: "pl",
//     basePrice: 300,
//     indicators: this.fotoIndicators
//   },
// {
//   id: 2,
//   name: "Photo",
//   description: "Description",
//   locale: "en",
//   basePrice: 300,
//   indicators: this.fotoIndicators
// },
// {
//   id: 3,
//   name: "film",
//   description: "opis filmu",
//   locale: "pl",
//   basePrice: 600,
//   indicators: this.filmIndicators
// },
// {
//   id: 4,
// name: "Film",
// description: "Description",
// locale: "en",
// basePrice: 600,
// indicators: this.filmIndicators
// }];

@Injectable({
  providedIn: 'root'
})
export class ServiceService {
  private subscription: Subscription = new Subscription();
  private servicesDataSource: BehaviorSubject<ServiceEto[]> = new BehaviorSubject([]);
  public servicesData = this.servicesDataSource.asObservable();

  constructor(
    private http: HttpClient,
    private snackbar: MatSnackBar
    ) { }

  public getAllServices() {
    return new Promise((resolve, reject) => {
    this.subscription.add(this.http.get<ServiceEto[]>(`${ServiceHandlingRestServicePaths.FIND_ALL_SERVICES()}`).subscribe(
      (services: ServiceEto[]) => {
        console.log(services)
        this.snackbar
        this.servicesDataSource.next(services);
        resolve(services);
      },
      (e) => {
        this.snackbar
        reject();
      }))
    })
  }

  ngOnDestroy(){
    this.subscription.unsubscribe();
  }
}
