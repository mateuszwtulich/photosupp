import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TranslateService } from '@ngx-translate/core';
import { BehaviorSubject, Subscription } from 'rxjs';
import { ServiceHandlingRestServicePaths } from 'src/app/servicehandling/rest-service-paths/ServiceHandlingRestServicePaths';
import { LocalStorageService } from 'src/app/shared/cache/localStorage.service';
import { BookingEto } from '../to/BookingEto';

@Injectable({
  providedIn: 'root'
})
export class BookingService {
  private subscription: Subscription = new Subscription();
  private bookingsDataSource: BehaviorSubject<BookingEto[]> = new BehaviorSubject([]);
  public bookingsData = this.bookingsDataSource.asObservable();
  private userBookingsDataSource: BehaviorSubject<BookingEto[]> = new BehaviorSubject([]);
  public userBookingsData = this.bookingsDataSource.asObservable();

  constructor(
    private http: HttpClient,
    private snackbar: MatSnackBar,
    private translate: TranslateService,
    private localStorage: LocalStorageService
    ) { }

  public getAllBookings() {
    return new Promise((resolve, reject) => {
    this.subscription.add(this.http.get<BookingEto[]>(`${ServiceHandlingRestServicePaths.FIND_ALL_BOOKINGS()}`).subscribe(
      (bookings: BookingEto[]) => {
        this.bookingsDataSource.next(bookings);
        resolve(bookings);
      },
      (e) => {
        this.snackbar.open(this.translate.instant('server.error'))
        reject();
      }))
    })
  }

  public getAllBookingsOfUser() {
    return new Promise((resolve, reject) => {
      let userId = this.localStorage.getUserId();
    this.subscription.add(this.http.get<BookingEto[]>(`${ServiceHandlingRestServicePaths.FIND_ALL_BOOKINGS_BY_USER(userId)}`).subscribe(
      (bookings: BookingEto[]) => {
        this.userBookingsDataSource.next(bookings);
        resolve(bookings);
      },
      (e) => {
        this.snackbar.open(this.translate.instant('server.error'))
        reject();
      }))
    })
  }

  ngOnDestroy(){
    this.subscription.unsubscribe();
  }
}