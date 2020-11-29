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

  constructor(
    private http: HttpClient,
    private snackbar: MatSnackBar,
    private translate: TranslateService,
    private localStorage: LocalStorageService,
    ) { }

  public getAllOrders() {
    return new Promise((resolve, reject) => {
    this.subscription.add(this.http.get<OrderEto[]>(`${OrderRestServicePaths.FIND_ALL_ORDERS()}`).subscribe(
      (orders: OrderEto[]) => {
        this.ordersDataSource.next(orders);
        resolve(orders);
      },
      (e) => {
        this.snackbar.open(this.translate.instant('server.error'))
        reject();
      }))
    })
  }

  public getOrdersOfUser() {
    return new Promise((resolve, reject) => {
      let userId = this.localStorage.getUserId();
    this.subscription.add(this.http.get<OrderEto[]>(`${OrderRestServicePaths.FIND_ALL_ORDERS_BY_USER(userId)}`).subscribe(
      (orders: OrderEto[]) => {
        this.userOrdersDataSource.next(orders);
        resolve(orders);
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