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
import { OrderStatus } from '../shared/enum/OrderStatus';
import { OrderEto } from '../shared/to/OrderEto';

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

const servicesStored: ServiceEto[] = [{
  id: 1,
  name: "foto",
  description: "opis",
  locale: "pl",
  basePrice: 300,
  indicators: fotoIndicators
},
{
  id: 2,
  name: "Photo",
  description: "Description",
  locale: "en",
  basePrice: 300,
  indicators: fotoIndicators
},
{
  id: 3,
  name: "film",
  description: "opis filmu",
  locale: "pl",
  basePrice: 600,
  indicators: filmIndicators
},
{
  id: 4,
  name: "Film",
  description: "Description",
  locale: "en",
  basePrice: 600,
  indicators: filmIndicators
}];

const COORDINATOR: UserEto = {
  name: "John",
  surname: "Smith",
  account: null,
  role: null
}

const USER: UserEto = {
  name: "Tom",
  surname: "Willman",
  account: null,
  role: null
}

const ORDERS: OrderEto[] = [
  { orderNumber: "INVIU0001", coordinator: COORDINATOR, user: USER, status: OrderStatus.NEW, booking: null, price: 1000, createdAt: "22-11-2020" },
  { orderNumber: "INVIU0002", coordinator: COORDINATOR, user: USER, status: OrderStatus.IN_PROGRESS, booking: null, price: 1000, createdAt: "20-11-2020" },
  { orderNumber: "INVIU0003", coordinator: COORDINATOR, user: USER, status: OrderStatus.TO_VERIFY, booking: null, price: 1000, createdAt: "19-11-2020" },
  { orderNumber: "INVIU0004", coordinator: COORDINATOR, user: USER, status: OrderStatus.FINISHED, booking: null, price: 1000, createdAt: "15-11-2020" },
  { orderNumber: "INVIU0005", coordinator: COORDINATOR, user: USER, status: OrderStatus.IN_PROGRESS, booking: null, price: 1000, createdAt: "19-11-2020" },
  { orderNumber: "INVIU0006", coordinator: COORDINATOR, user: USER, status: OrderStatus.NEW, booking: null, price: 1000, createdAt: "22-11-2020" },
];

@Component({
  selector: 'cf-orders-overview',
  templateUrl: './orders-overview.component.html',
  styleUrls: ['./orders-overview.component.scss']
})

export class OrdersOverviewComponent implements OnInit {
  displayedColumns: string[] = ['orderNumber', 'coordinator', 'user', 'status', 'booking', 'price', 'createdAt', 'actions'];
  dataSource = new MatTableDataSource(ORDERS);
  isSpinnerDisplayed = false;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private translate: TranslateService, private router: Router) { }

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

  private prepareFilterPredicate(): (data: OrderEto, filter: string) => boolean {
    return (data: OrderEto, filter: string) => {
      return data.user.name.toLocaleLowerCase().includes(filter) || data.user.surname.toLocaleLowerCase().includes(filter) ||
        this.translate.instant("orders." + data.status).toLocaleLowerCase().includes(filter) || data.price.toFixed().includes(filter) ||
        data.orderNumber.toLocaleLowerCase().includes(filter) || data.createdAt.toLocaleLowerCase().includes(filter) ||
        data.coordinator.name.toLocaleLowerCase().includes(filter) || data.coordinator.surname.toLocaleLowerCase().includes(filter) || 
        (data.booking ? data.booking.name.toLocaleLowerCase().includes(filter) : this.translate.instant("orders.booking-null").toLocaleLowerCase().includes(filter))
    };
  }

  filterStatus(status: string) {
    if (status != "ALL") {
      this.dataSource.filter = this.translate.instant("orders." + status).trim().toLowerCase();
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
        case "orderNumber":
          return SortUtil.compare(a.user.surname, b.user.surname, isAsc);
        case "coordinator":
          return SortUtil.compare(a.coordinator.surname, b.coordinator.surname, isAsc);
        case "user":
          return SortUtil.compare(a.user.surname, b.user.surname, isAsc);
        case "status":
          return SortUtil.compare(a.status, b.status, isAsc);
        case "booking":
          return SortUtil.compare(a.booking ? a.booking.name : "", b.booking ? b.booking.name : "", isAsc);
        case "price":
          return SortUtil.compare(a.price, b.price, isAsc);
        case "createdAt":
          return SortUtil.compare(a.createdAt, b.createdAt, isAsc);
        default:
          return 0;
      }
    });
  }

  navigateToOrderDetails(orderNumber: string){
    let currentHeadLink = this.router.url.substring(0,this.router.url.indexOf("o"));
    
    this.router.navigateByUrl(currentHeadLink + "orders/details/" + orderNumber);
  }

  navigateToBookingDetails(id: number){
    let currentHeadLink = this.router.url.substring(0,this.router.url.indexOf("o"));
    
    this.router.navigateByUrl(currentHeadLink + "orders/booking/details/" + id.toFixed());
  }
}
