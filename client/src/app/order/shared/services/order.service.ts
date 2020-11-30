import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TranslateService } from '@ngx-translate/core';
import { BehaviorSubject, Subscription } from 'rxjs';
import { LocalStorageService } from 'src/app/shared/cache/localStorage.service';
import { OrderRestServicePaths } from '../rest-service-paths/OrderRestServicePaths';
import { OrderEto } from '../to/OrderEto';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private subscription: Subscription = new Subscription();
  private ordersDataSource: BehaviorSubject<OrderEto[]> = new BehaviorSubject([]);
  public ordersData = this.ordersDataSource.asObservable();
  private userOrdersDataSource: BehaviorSubject<OrderEto[]> = new BehaviorSubject([]);
  public userOrdersData = this.ordersDataSource.asObservable();
  private spinnerDataSource: BehaviorSubject<boolean> = new BehaviorSubject(false);
  public spinnerData = this.spinnerDataSource.asObservable();

  constructor(
    private http: HttpClient,
    private snackbar: MatSnackBar,
    private translate: TranslateService,
    private localStorage: LocalStorageService,
    ) { }

  public getAllOrders() {
    return new Promise((resolve, reject) => {
      this.spinnerDataSource.next(true);
    this.subscription.add(this.http.get<OrderEto[]>(`${OrderRestServicePaths.FIND_ALL_ORDERS()}`).subscribe(
      (orders: OrderEto[]) => {
        this.ordersDataSource.next(orders);
        this.spinnerDataSource.next(false);
        resolve(orders);
      },
      (e) => {
        this.snackbar.open(this.translate.instant('server.error'));
        this.spinnerDataSource.next(false);
        reject();
      }))
    })
  }

  public getOrdersOfUser() {
    return new Promise((resolve, reject) => {
      this.spinnerDataSource.next(true);
      let userId = this.localStorage.getUserId();
    this.subscription.add(this.http.get<OrderEto[]>(`${OrderRestServicePaths.FIND_ALL_ORDERS_BY_USER(userId)}`).subscribe(
      (orders: OrderEto[]) => {
        this.userOrdersDataSource.next(orders);
        this.spinnerDataSource.next(false);
        resolve(orders);
      },
      (e) => {
        this.snackbar.open(this.translate.instant('server.error'))
        this.spinnerDataSource.next(false);
        reject();
      }))
    })
  }

  ngOnDestroy(){
    this.subscription.unsubscribe();
  }
}