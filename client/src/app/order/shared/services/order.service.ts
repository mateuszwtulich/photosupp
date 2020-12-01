import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TranslateService } from '@ngx-translate/core';
import { BehaviorSubject, Subscription } from 'rxjs';
import { LocalStorageService } from 'src/app/shared/cache/localStorage.service';
import { OrderRestServicePaths } from '../rest-service-paths/OrderRestServicePaths';
import { CommentEto } from '../to/CommentEto';
import { CommentTo } from '../to/CommentTo';
import { MediaContentEto } from '../to/MediaContentEto';
import { OrderEto } from '../to/OrderEto';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private subscription: Subscription = new Subscription();
  private ordersDataSource: BehaviorSubject<OrderEto[]> = new BehaviorSubject([]);
  public ordersData = this.ordersDataSource.asObservable();
  private userOrdersDataSource: BehaviorSubject<OrderEto[]> = new BehaviorSubject([]);
  public userOrdersData = this.userOrdersDataSource.asObservable();
  private spinnerDataSource: BehaviorSubject<boolean> = new BehaviorSubject(false);
  public spinnerData = this.spinnerDataSource.asObservable();
  private orderDetailsDataSource: BehaviorSubject<OrderEto> = new BehaviorSubject(null);
  public orderDetailsData = this.orderDetailsDataSource.asObservable();
  private commentsOrderDataSource: BehaviorSubject<CommentEto[]> = new BehaviorSubject([]);
  public commentOrderData = this.commentsOrderDataSource.asObservable();
  private mediaContentOrderDataSource: BehaviorSubject<MediaContentEto[]> = new BehaviorSubject([]);
  public mediaContentOrderData = this.mediaContentOrderDataSource.asObservable();

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

  public getOrderByOrderNumber(orderNumber: string) {
    return new Promise((resolve, reject) => {
      this.spinnerDataSource.next(true);
      this.subscription.add(this.http.get<OrderEto>(`${OrderRestServicePaths.ORDER_PATH_WITH_ORDER_NUMBER(orderNumber)}`).subscribe(
        (order: OrderEto) => {
          this.orderDetailsDataSource.next(order);
          this.spinnerDataSource.next(false);
          resolve(order);
        },
        (e) => {
          this.snackbar.open(this.translate.instant('server.error'));
          this.spinnerDataSource.next(false);
          reject();
        }))
    })
  }

  public acceptOrder(orderNumber: string) {
    return new Promise((resolve, reject) => {
      this.spinnerDataSource.next(true);
      this.subscription.add(this.http.put<OrderEto>(`${OrderRestServicePaths.ACCEPT_ORDER_PATH(orderNumber)}`, {}).subscribe(
        (order: OrderEto) => {
          this.orderDetailsDataSource.next(order);
          this.spinnerDataSource.next(false);
          resolve(order);
        },
        (e) => {
          this.snackbar.open(this.translate.instant('server.error'));
          this.spinnerDataSource.next(false);
          reject();
        }))
    })
  }

  public sendOrderToVerification(orderNumber: string) {
    return new Promise((resolve, reject) => {
      this.spinnerDataSource.next(true);
      this.subscription.add(this.http.put<OrderEto>(`${OrderRestServicePaths.SEND_TO_VERFICATION_ORDER_PATH(orderNumber)}`, {}).subscribe(
        (order: OrderEto) => {
          this.orderDetailsDataSource.next(order);
          this.spinnerDataSource.next(false);
          resolve(order);
        },
        (e) => {
          this.snackbar.open(this.translate.instant('server.error'));
          this.spinnerDataSource.next(false);
          reject();
        }))
    })
  }

  public finishOrder(orderNumber: string) {
    return new Promise((resolve, reject) => {
      this.spinnerDataSource.next(true);
      this.subscription.add(this.http.put<OrderEto>(`${OrderRestServicePaths.FINISH_ORDER_PATH(orderNumber)}`, {}).subscribe(
        (order: OrderEto) => {
          this.orderDetailsDataSource.next(order);
          this.spinnerDataSource.next(false);
          resolve(order);
        },
        (e) => {
          this.snackbar.open(this.translate.instant('server.error'));
          this.spinnerDataSource.next(false);
          reject();
        }))
    })
  }

  public getCommentsOfOrder(orderNumber: string) {
    return new Promise((resolve, reject) => {
      this.spinnerDataSource.next(true);
      this.subscription.add(this.http.get<CommentEto[]>(`${OrderRestServicePaths.FIND_ALL_COMMENTS_BY_ORDER_NUMBER(orderNumber)}`).subscribe(
        (comments: CommentEto[]) => {
          this.commentsOrderDataSource.next(comments);
          this.spinnerDataSource.next(false);
          resolve(comments);
        },
        (e) => {
          this.snackbar.open(this.translate.instant('server.error'))
          this.spinnerDataSource.next(false);
          reject();
        }))
    })
  }

  public addComment(commentTo: CommentTo) {
    return new Promise((resolve, reject) => {
      this.spinnerDataSource.next(true);
      this.subscription.add(this.http.post<CommentEto>(`${OrderRestServicePaths.COMMENT_PATH()}`, commentTo).subscribe(
        (comment: CommentEto) => {
          console.log(this.commentsOrderDataSource.value)
          if (this.commentsOrderDataSource.value) {
            const currentValue = this.commentsOrderDataSource.value;
            const updatedValue = [...currentValue, comment];
            this.commentsOrderDataSource.next(updatedValue);
          } else {
            this.commentsOrderDataSource.next([comment]);
          }
          this.spinnerDataSource.next(false);
          resolve(comment);
        },
        (e) => {
          this.snackbar.open(this.translate.instant('server.error'))
          this.spinnerDataSource.next(false);
          reject();
        }))
    })
  }

  public getMediaContentOfOrder(orderNumber: string) {
    return new Promise((resolve, reject) => {
      this.spinnerDataSource.next(true);
      this.subscription.add(this.http.get<MediaContentEto[]>(`${OrderRestServicePaths.MEDIA_CONTENT_BY_ORDER_NUMBER(orderNumber)}`).subscribe(
        (mediaContent: MediaContentEto[]) => {
          this.mediaContentOrderDataSource.next(mediaContent);
          this.spinnerDataSource.next(false);
          resolve(mediaContent);
        },
        (e) => {
          this.snackbar.open(this.translate.instant('server.error'))
          this.spinnerDataSource.next(false);
          reject();
        }))
    })
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
}