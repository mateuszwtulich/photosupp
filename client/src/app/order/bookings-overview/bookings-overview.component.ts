import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort, Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { SortUtil } from 'src/app/core/utils/SortUtil';
import { IndicatorEto } from 'src/app/servicehandling/to/IndicatorEto';
import { ServiceEto } from 'src/app/servicehandling/to/ServiceEto';
import { UserEto } from 'src/app/usermanagement/shared/to/UserEto';
import { AddressEto } from '../shared/to/AddressEto';
import { BookingEto } from '../shared/to/BookingEto';

const fuelIndicatorPL = {
  id: 3,
  name: "Odległość od Częstochowy",
  description: "Proszę podać liczbę kilometrów Państwa lokalizacji od Częstochowy",
  locale: "pl",
  baseAmount: 20,
  doublePrice: 20
}

const fuelIndicatorEN = {
  id: 4,
  name: "Distance from Czestochowa",
  description: "Kindly provide number of kilometers to your localization from Czestochowa",
  locale: "en",
  baseAmount: 20,
  doublePrice: 20
}

const fotoIndicators: IndicatorEto[] = [{
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
fuelIndicatorPL,
fuelIndicatorEN
]

const filmIndicators: IndicatorEto[] = [{
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
fuelIndicatorPL,
fuelIndicatorEN
]

const SERVICE: ServiceEto = {
  id: 1,
  name: "foto",
  description: "opis",
  locale: "pl",
  basePrice: 300,
  indicators: fotoIndicators
};

const USER: UserEto = {
  name: "Tom",
  surname: "Willman",
  account: null,
  role: null
}

const ADDRESS: AddressEto = {
  city: "Wroclaw",
  street: "Wroblewskiego",
  buildingNumber: "20A",
  apartmentNumber: null,
  postalCode: "60-324",
}

const BOOKINGS: BookingEto[] = [
  {id: 1, name: "Booking #1", description: "short description", service: SERVICE, address: ADDRESS, user: USER, isConfirmed: true, predictedPrice: 1000, start: "22-11-2020", end: "20-11-2020", modificationDate: "22-11-2020", priceIndicatorList: null},
  {id: 2, name: "Booking #1", description: "short description", service: SERVICE, address: ADDRESS, user: USER, isConfirmed: true, predictedPrice: 1000, start: "22-11-2020", end: "20-11-2020", modificationDate: "22-11-2020", priceIndicatorList: null},
  {id: 3, name: "Booking #1", description: "short description", service: SERVICE, address: ADDRESS, user: USER, isConfirmed: true, predictedPrice: 1000, start: "22-11-2020", end: "20-11-2020", modificationDate: "22-11-2020", priceIndicatorList: null}, 
  {id: 4, name: "Booking #1", description: "short description", service: SERVICE, address: ADDRESS, user: USER, isConfirmed: true, predictedPrice: 1000, start: "22-11-2020", end: "20-11-2020", modificationDate: "22-11-2020", priceIndicatorList: null}
];

@Component({
  selector: 'cf-bookings-overview',
  templateUrl: './bookings-overview.component.html',
  styleUrls: ['./bookings-overview.component.scss']
})

export class BookingsOverviewComponent implements OnInit {
  displayedColumns: string[] = ['name', 'service', 'address', 'user', 'isConfirmed', 'predictedPrice', 'start', 'end', 'actions'];
  dataSource = new MatTableDataSource(BOOKINGS);
  isSpinnerDisplayed = false;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private translate: TranslateService, private router: Router) {
   }

   ngOnInit(): void {
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
    this.dataSource.filterPredicate = this.prepareFilterPredicate();
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  private prepareFilterPredicate(): (data: BookingEto, filter: string) => boolean {
    return (data: BookingEto, filter: string) => {
      return data.user.name.toLocaleLowerCase().includes(filter) || data.user.surname.toLocaleLowerCase().includes(filter) ||
        (data.isConfirmed ? this.translate.instant("bookings.confirmed").toLocaleLowerCase().includes(filter) : 
        this.translate.instant("bookings.confirmed").toLocaleLowerCase().includes(filter)) || data.start.includes(filter) || 
        data.end.includes(filter) || data.predictedPrice.toFixed().includes(filter) || data.service.name.toLocaleLowerCase().includes(filter) || 
        data.address.city.toLocaleLowerCase().includes(filter) || data.address.street.toLocaleLowerCase().includes(filter) || 
        data.address.buildingNumber.toLocaleLowerCase().includes(filter) || data.name.toLocaleLowerCase().includes(filter);
    };
  }

  filterStatus(status: string) {
    if (status != "all") {
      this.dataSource.filter = this.translate.instant("bookings." + status).trim().toLowerCase();
    } else {
      this.dataSource.filter = "";
    }
  }

  sortData(sort: Sort) {
    const data = this.dataSource.data.slice();
    if (!sort.active || sort.direction === "") {
      this.dataSource.data = data;
    }
    this.dataSource.data = data.sort((a, b) => {
      const isAsc = sort.direction === "asc";
      switch (sort.active) {
        case "name":
          return SortUtil.compare(a.name, b.name, isAsc);
        case "coordinator":
          return SortUtil.compare(a.service.name, b.service.name, isAsc);
        case "user":
          return SortUtil.compare(a.user.surname, b.user.surname, isAsc);
        case "address":
          return SortUtil.compare(a.address.city, b.address.city, isAsc);
        case "isConfirmed":
          return SortUtil.compare(
            this.translate.instant("bookings." + a.isConfirmed), 
            this.translate.instant("bookings." + b.isConfirmed), isAsc);
        case "predictedPrice":
          return SortUtil.compare(a.predictedPrice, b.predictedPrice, isAsc);
        case "start":
          return SortUtil.compare(a.start, b.start, isAsc);
        case "end":
          return SortUtil.compare(a.end, b.end, isAsc);
        default:
          return 0;
      }
    });
  }

  navigateToBookingDetails(id: number){
    let currentHeadLink = this.router.url.substring(0,this.router.url.indexOf("o"));
    
    this.router.navigateByUrl(currentHeadLink + "orders/booking/details/" + id.toFixed());
  }
}
