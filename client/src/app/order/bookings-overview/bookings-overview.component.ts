import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort, Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { NgxPermissionsService } from 'ngx-permissions';
import { Subscription } from 'rxjs';
import { IndicatorEto } from 'src/app/servicehandling/to/IndicatorEto';
import { ServiceEto } from 'src/app/servicehandling/to/ServiceEto';
import { ApplicationPermission } from 'src/app/shared/utils/ApplicationPermission';
import { SortUtil } from 'src/app/shared/utils/SortUtil';
import { UserEto } from 'src/app/usermanagement/shared/to/UserEto';
import { BookingService } from '../shared/services/booking.service';
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
  indicatorEtoList: fotoIndicators
};

const USER: UserEto = {
  id: 1,
  name: "Tom",
  surname: "Willman",
  accountEto: null,
  roleEto: null
}

const ADDRESS: AddressEto = {
  id: 1,
  city: "Wroclaw",
  street: "Wroblewskiego",
  buildingNumber: "20A",
  apartmentNumber: null,
  postalCode: "60-324",
}

const BOOKINGS: BookingEto[] = [
  {id: 1, name: "Booking #1", description: "short description", serviceEto: SERVICE, addressEto: ADDRESS, userEto: USER, isConfirmed: true, predictedPrice: 1000, start: "22-11-2020", end: "20-11-2020", modificationDate: "22-11-2020", priceIndicatorList: null},
  {id: 2, name: "Booking #1", description: "short description", serviceEto: SERVICE, addressEto: ADDRESS, userEto: USER, isConfirmed: true, predictedPrice: 1000, start: "22-11-2020", end: "20-11-2020", modificationDate: "22-11-2020", priceIndicatorList: null},
  {id: 3, name: "Booking #1", description: "short description", serviceEto: SERVICE, addressEto: ADDRESS, userEto: USER, isConfirmed: true, predictedPrice: 1000, start: "22-11-2020", end: "20-11-2020", modificationDate: "22-11-2020", priceIndicatorList: null}, 
  {id: 4, name: "Booking #1", description: "short description", serviceEto: SERVICE, addressEto: ADDRESS, userEto: USER, isConfirmed: true, predictedPrice: 1000, start: "22-11-2020", end: "20-11-2020", modificationDate: "22-11-2020", priceIndicatorList: null}
];

@Component({
  selector: 'cf-bookings-overview',
  templateUrl: './bookings-overview.component.html',
  styleUrls: ['./bookings-overview.component.scss']
})

export class BookingsOverviewComponent implements OnInit {
  public displayedColumns: string[] = ['name', 'service', 'address', 'user', 'isConfirmed', 'predictedPrice', 'start', 'end', 'actions'];
  public dataSource: MatTableDataSource<BookingEto>;
  public isSpinnerDisplayed = false;
  private subscription = new Subscription; 
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(
    private translate: TranslateService,
    private router: Router,
    private permissionsService: NgxPermissionsService,
    private bookingService: BookingService
    ) {
   }

   ngOnInit(): void {
    this.loadsBooking();
  }

  private loadsBooking(){
    this.permissionsService.hasPermission(ApplicationPermission.A_CRUD_SUPER).then((result) => {
      if (result) {
        this.loadsAllBookings();
      } else {
        this.permissionsService.hasPermission(ApplicationPermission.A_CRUD_ORDERS).then((result) => {
          if (result) {
            this.loadsAllBookings();
          } else {
            this.permissionsService.hasPermission(ApplicationPermission.AUTH_USER).then((result) => {
              if (result) {
                this.loadsUserBookings();
              }
            })
          }
        })
      }
    })  
  }

  private loadsUserBookings(){
    this.bookingService.getAllBookingsOfUser();

    this.subscription.add(this.bookingService.userBookingsData.subscribe(
      (bookings) => {
        this.dataSource = new MatTableDataSource(bookings);
        this.setDataSourceSettings();
    }))
  }

  private loadsAllBookings(){
    this.bookingService.getAllBookings();

    this.subscription.add(this.bookingService.bookingsData.subscribe(
      (bookings) => {
        this.dataSource = new MatTableDataSource(bookings);
        this.setDataSourceSettings();
    }))
  }

  private setDataSourceSettings() {
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
      return data.userEto.name.toLocaleLowerCase().includes(filter) || data.userEto.surname.toLocaleLowerCase().includes(filter) ||
        (data.isConfirmed ? this.translate.instant("bookings.confirmed").toLocaleLowerCase().includes(filter) : 
        this.translate.instant("bookings.confirmed").toLocaleLowerCase().includes(filter)) || data.start.includes(filter) || 
        data.end.includes(filter) || data.predictedPrice.toFixed().includes(filter) || data.serviceEto.name.toLocaleLowerCase().includes(filter) || 
        data.addressEto.city.toLocaleLowerCase().includes(filter) || data.addressEto.street.toLocaleLowerCase().includes(filter) || 
        data.addressEto.buildingNumber.toLocaleLowerCase().includes(filter) || data.name.toLocaleLowerCase().includes(filter);
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
          return SortUtil.compare(a.serviceEto.name, b.serviceEto.name, isAsc);
        case "user":
          return SortUtil.compare(a.userEto.surname, b.userEto.surname, isAsc);
        case "address":
          return SortUtil.compare(a.addressEto.city, b.addressEto.city, isAsc);
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
