import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort, Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute } from '@angular/router';
import { ServiceEto } from 'src/app/servicehandling/to/ServiceEto';
import { SortUtil } from 'src/app/shared/utils/SortUtil';
import { ApplicationPermissions } from 'src/app/usermanagement/shared/enum/ApplicationPermissions';
import { AccountEto } from 'src/app/usermanagement/shared/to/AccountEto';
import { PermissionEto } from 'src/app/usermanagement/shared/to/PermissionEto';
import { RoleEto } from 'src/app/usermanagement/shared/to/RoleEto';
import { UserEto } from 'src/app/usermanagement/shared/to/UserEto';
import { AddressEto } from '../shared/to/AddressEto';
import { BookingEto } from '../shared/to/BookingEto';

const BASIC_PERM: PermissionEto[] = [{
  name: ApplicationPermissions.AUTH_USER,
  description: "Basic permissions"
}]

const ROLE1: RoleEto = {
  id: 2,
  name: "User",
  description: "Description of normal user",
  permissions: BASIC_PERM
}

const ACCOUNT2: AccountEto = {
  id: 1,
  username: "test2",
  password: "dsf",
  email: "test2@test.com",
  isActivated: true
}

const USER: UserEto = {
  id:1,
  name: "Tom",
  surname: "Willman",
  accountEto: ACCOUNT2,
  roleEto: ROLE1
}

const SERVICE: ServiceEto = {
  id: 1,
  name: "foto",
  description: "opis",
  locale: "pl",
  basePrice: 300,
  indicatorEtoList: null
};

const ADDRESS: AddressEto = {
  id: 1,
  city: "Wroclaw",
  street: "Wroblewskiego",
  buildingNumber: "20A",
  apartmentNumber: null,
  postalCode: "60-324",
}

const INDICATORS = [{
  id: 3,
  name: "Odległość od Częstochowy",
  description: "Proszę podać liczbę kilometrów Państwa lokalizacji od Częstochowy",
  locale: "pl",
  baseAmount: 20,
  doublePrice: 20
},
{
  id: 4,
  name: "Distance from Czestochowa",
  description: "Kindly provide number of kilometers to your localization from Czestochowa",
  locale: "en",
  baseAmount: 20,
  doublePrice: 20
},
{
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
{
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
}
]

const PRICE_INDICATORS = [
  {
    indicator: INDICATORS[0],
    bookingId: 1,
    price: 300,
    amount: 200
  },
  {
    indicator: INDICATORS[6],
    bookingId: 1,
    price: 30,
    amount: 2
  },
  {
    indicator: INDICATORS[4],
    bookingId: 1,
    price: 324,
    amount: 3
  }
]

const BOOKING: BookingEto = {id: 1, name: "Booking #1", description: "short descriptionssss sssssssssssssss ssssssssss sssssssss ssssssss sssssss sssss ssss", serviceEto: SERVICE, addressEto: ADDRESS, userEto: USER, confirmed: true, predictedPrice: 1000, start: "22-11-2020", end: "20-11-2020", modificationDate: "22-11-2020", priceIndicatorEtoList: PRICE_INDICATORS};

@Component({
  selector: 'cf-booking-details',
  templateUrl: './booking-details.component.html',
  styleUrls: ['./booking-details.component.scss']
})
export class BookingDetailsComponent implements OnInit {
  public bookingControl: FormControl;
  booking = BOOKING;
  displayedColumns: string[] = ['name', 'description', 'amount', 'price', 'actions'];
  dataSource = new MatTableDataSource(PRICE_INDICATORS);
  isSpinnerDisplayed = false;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.bookingControl = new FormControl(BOOKING);
    this.route.snapshot.paramMap.get('id');
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
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
          return SortUtil.compare(a.indicator.name, b.indicator.name, isAsc);
        case "description":
          return SortUtil.compare(a.indicator.description, b.indicator.description, isAsc);
        case "amount":
          return SortUtil.compare(a.amount, b.amount, isAsc);
        case "price":
          return SortUtil.compare(a.price, b.price, isAsc);
        default:
          return 0;
      }
    });
  }
}
