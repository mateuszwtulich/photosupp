import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort, Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { PriceIndicatorEto } from 'src/app/core/to/PriceIndicatorEto';
import { SortUtil } from 'src/app/shared/utils/SortUtil';
import { BookingService } from '../shared/services/booking.service';
import { BookingEto } from '../shared/to/BookingEto';
import { BookingEtoWithOrderNumber } from '../shared/to/BookingEtoWithOrderNumber';

@Component({
  selector: 'cf-booking-details',
  templateUrl: './booking-details.component.html',
  styleUrls: ['./booking-details.component.scss']
})
export class BookingDetailsComponent implements OnInit {
  public bookingControl: FormControl;
  public booking: BookingEto;
  public displayedColumns: string[] = ['name', 'description', 'amount', 'price', 'actions'];
  public dataSource: MatTableDataSource<PriceIndicatorEto>;
  public isSpinnerDisplayed = false;
  public subscription = new Subscription();
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(
    private route: ActivatedRoute,
    private bookingService: BookingService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.onSpinnerDisplayed();
    this.bookingControl = new FormControl("", Validators.required);
    this.getBookingById(this.route.snapshot.paramMap.get('id'));
  }

  private getBookingById(id: string) {
    this.bookingService.getBookingById(id).then(() => {
      this.subscription.add(this.bookingService.bookingDetailsData.subscribe((booking: BookingEto) => {
        this.dataSource = new MatTableDataSource(booking.priceIndicatorEtoList);
        this.booking = booking;
        this.bookingControl.setValue(booking);
        this.setDataSource();
      }))
    });
  }

  private setDataSource() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  private onSpinnerDisplayed() {
    this.subscription.add(this.bookingService.spinnerData.subscribe((isSpinnerDisplayed: boolean) => {
      this.isSpinnerDisplayed = isSpinnerDisplayed;
    }));
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
          return SortUtil.compare(a.indicatorEto.name, b.indicatorEto.name, isAsc);
        case "description":
          return SortUtil.compare(a.indicatorEto.description, b.indicatorEto.description, isAsc);
        case "amount":
          return SortUtil.compare(a.amount, b.amount, isAsc);
        case "price":
          return SortUtil.compare(a.price, b.price, isAsc);
        default:
          return 0;
      }
    });
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  confirmBooking() {
    let currentHeadLink = this.router.url.substring(0, this.router.url.indexOf("o"));

    this.bookingService.confirmBooking(this.booking.id).then((booking: BookingEtoWithOrderNumber) => {
      this.router.navigateByUrl(currentHeadLink + "orders/details/" + booking.orderNumber);
    })
  }
}
