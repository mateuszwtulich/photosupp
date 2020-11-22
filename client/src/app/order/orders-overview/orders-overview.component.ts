import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort, Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { UserEto } from 'src/app/usermanagement/shared/to/UserEto';
import { OrderStatus } from '../shared/enum/OrderStatus';
import { BookingEto } from '../shared/to/BookingEto';
import { OrderEto } from '../shared/to/OrderEto';

const COORDINATOR: UserEto = {
  name: "John",
  surname: "Smith",
  accountEto: null,
  roleEto: null
}

const USER: UserEto = {
  name: "Tom",
  surname: "Willman",
  accountEto: null,
  roleEto: null
}

const ORDERS: BookingEto[] = [
  {name: "INVIU0001", coordinator: COORDINATOR, user: USER, status: OrderStatus.NEW, booking: null, price: 1000, createdAt: "22-11-2020"},
  {orderNumber: "INVIU0002", coordinator: COORDINATOR, user: USER, status: OrderStatus.IN_PROGRESS, booking: null, price: 1000, createdAt: "20-11-2020"},
  {orderNumber: "INVIU0003", coordinator: COORDINATOR, user: USER, status: OrderStatus.TO_VERIFY, booking: null, price: 1000, createdAt: "19-11-2020"},
  {orderNumber: "INVIU0004", coordinator: COORDINATOR, user: USER, status: OrderStatus.FINISHED, booking: null, price: 1000, createdAt: "15-11-2020"},
  {orderNumber: "INVIU0005", coordinator: COORDINATOR, user: USER, status: OrderStatus.IN_PROGRESS, booking: null, price: 1000, createdAt: "19-11-2020"},
  {orderNumber: "INVIU0006", coordinator: COORDINATOR, user: USER, status: OrderStatus.NEW, booking: null, price: 1000, createdAt: "22-11-2020"},
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

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  constructor() { }

  ngOnInit(): void {
  }

  sortData(sort: Sort) {
  }

}
