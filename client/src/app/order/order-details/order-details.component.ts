import { Component, Inject, Input, OnInit, ViewChild } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatFormField } from '@angular/material/form-field';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort, Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import { TranslateService, LangChangeEvent } from '@ngx-translate/core';
import { Gallery } from 'angular-gallery';
import { combineLatest, Observable, Subscription } from 'rxjs';
import { combineAll } from 'rxjs/operators';
import { ServiceEto } from 'src/app/servicehandling/to/ServiceEto';
import { LocalStorageService } from 'src/app/shared/cache/localStorage.service';
import { SortUtil } from 'src/app/shared/utils/SortUtil';
import { ApplicationPermissions } from 'src/app/usermanagement/shared/enum/ApplicationPermissions';
import { AccountEto } from 'src/app/usermanagement/shared/to/AccountEto';
import { PermissionEto } from 'src/app/usermanagement/shared/to/PermissionEto';
import { RoleEto } from 'src/app/usermanagement/shared/to/RoleEto';
import { UserEto } from 'src/app/usermanagement/shared/to/UserEto';
import { OrderModule } from '../order.module';
import { MediaType } from '../shared/enum/MediaType';
import { OrderStatus } from '../shared/enum/OrderStatus';
import { OrderService } from '../shared/services/order.service';
import { CommentEto } from '../shared/to/CommentEto';
import { CommentTo } from '../shared/to/CommentTo';
import { ImagePath } from '../shared/to/ImagePath';
import { MediaContentEto } from '../shared/to/MediaContentEto';
import { OrderEto } from '../shared/to/OrderEto';

@Component({
  selector: 'cf-order-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.scss']
})
export class OrderDetailsComponent implements OnInit {
  public displayedColumns: string[] = ['type', 'name', 'actions'];
  public dataSource: MatTableDataSource<MediaContentEto>;
  public isSpinnerDisplayed = false;
  public subscritpion: Subscription = new Subscription();
  public order: OrderEto;
  public subscription = new Subscription();
  public orderControl: FormControl;
  public commentControl: FormControl;
  public comments: Observable<CommentEto[]>;
  public mediaContents: MediaContentEto[];
  public imagePaths: ImagePath[] = [];
  public userId: number;
  public mediaExists = false;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(
    private translate: TranslateService,
    private router: Router,
    private gallery: Gallery,
    private route: ActivatedRoute,
    private orderSevice: OrderService,
    private localStorage: LocalStorageService,
  ) {
  }

  ngOnInit(): void {
    this.userId = this.localStorage.getUserId();
    this.onSpinnerDisplayed();
    this.orderControl = new FormControl("", Validators.required);
    this.commentControl = new FormControl("");
    this.getOrderById(this.route.snapshot.paramMap.get('orderNumber'));
  }

  private getOrderById(orderNumber: string) {
    Promise.all([
      this.orderSevice.getOrderByOrderNumber(orderNumber),
      this.orderSevice.getCommentsOfOrder(orderNumber),
      this.orderSevice.getMediaContentOfOrder(orderNumber)
    ]).then(() => {
      this.subscritpion.add(combineLatest(
        this.orderSevice.orderDetailsData,
        this.orderSevice.mediaContentOrderData
      ).subscribe(([order, mediaContents]) => {
        this.order = order;
        this.orderControl.setValue(order);
        this.comments = this.orderSevice.commentOrderData;
        this.mediaContents = mediaContents;
        this.mediaExists = this.mediaContents.length > 0;
        this.prepareMediaContentTable();
      }))
    })
  }

  private prepareMediaContentTable() {
    let dataSource = this.mediaContents.map((mediaContent: MediaContentEto) => {
      return {
        id: mediaContent.id,
        type: mediaContent.type,
        orderNumber: mediaContent.orderNumber,
        url: mediaContent.url.substring(mediaContent.url.lastIndexOf("/") + 1)
      }
    })
    this.dataSource = new MatTableDataSource(dataSource);
    this.setDataSource();
    this.loadImages(this.mediaContents);
  }

  private setDataSource() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  private onSpinnerDisplayed() {
    this.subscription.add(this.orderSevice.spinnerData.subscribe((isSpinnerDisplayed: boolean) => {
      this.isSpinnerDisplayed = isSpinnerDisplayed;
    }));
  }

  private loadImages(mediaContents) {
    this.imagePaths = [];
    mediaContents.forEach((mediaContent: MediaContentEto) => {
      this.imagePaths.push({
        path: mediaContent.url
      });
    })
  }

  ngOnDestroy() {
    this.subscritpion.unsubscribe();
  }

  navigateToBookingDetails(id: number) {
    let currentHeadLink = this.router.url.substring(0, this.router.url.indexOf("o"));

    this.router.navigateByUrl(currentHeadLink + "orders/booking/details/" + id.toFixed());
  }

  showGallery(index: number) {
    let prop = {
      images: this.imagePaths,
      index
    };
    this.gallery.load(prop);
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
          return SortUtil.compare(a.url, b.url, isAsc);
        case "type":
          return SortUtil.compare(a.type, b.type, isAsc);
        default:
          return 0;
      }
    });
  }

  public addComment() {
    if (this.commentControl.valid) {
      let commentTo: CommentTo = {
        content: this.commentControl.value,
        orderNumber: this.order.orderNumber,
        userId: this.localStorage.getUserId()
      }

      this.orderSevice.addComment(commentTo).then(() => {
        this.commentControl.setValue("");
      })
    }
  }

  public acceptOrder() {
    this.orderSevice.acceptOrder(this.order.orderNumber).then(() => {
      this.order.status = OrderStatus.IN_PROGRESS;
    });
  }

  public sendToVerficationOrder() {
    this.orderSevice.sendOrderToVerification(this.order.orderNumber).then(() => {
      this.order.status = OrderStatus.TO_VERIFY;
    });
  }

  public finishOrder() {
    this.orderSevice.finishOrder(this.order.orderNumber).then(() => {
      this.order.status = OrderStatus.FINISHED;
    });
  }
}


// @Component({
//   selector: 'order-details-modify-dialog',
//   templateUrl: 'order-details-modify-dialog.html',
//   styleUrls: ['./order-details.component.scss']
// })
// export class UserDetailsModifyDialog implements OnInit{
//   isSpinnerDisplayed = false;
//   subscription = new Subscription();
//   nameControl = new FormControl("", Validators.required);
//   surnameControl = new FormControl("", Validators.required);

//   constructor(
//     public dialogRef: MatDialogRef<UserDetailsModifyDialog>,
//     @Inject(MAT_DIALOG_DATA) public data: UserEto) { }

// ngOnInit(): void {
// }

// modifyUser() {
//   if(this.nameControl.valid && this.surnameControl.valid) {
//     this.dialogRef.close({
//       id: this.data.id,
//       role: this.data.role,
//       account: this.data.account,
//       name: this.nameControl.value,
//       surname: this.surnameControl.value
//     })
//   }
// }

// closeDialog() {
//   this.dialogRef.close();
// }
// }
