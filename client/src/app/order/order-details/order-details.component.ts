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
import { Subscription } from 'rxjs';
import { ServiceEto } from 'src/app/servicehandling/to/ServiceEto';
import { SortUtil } from 'src/app/shared/utils/SortUtil';
import { ApplicationPermissions } from 'src/app/usermanagement/shared/enum/ApplicationPermissions';
import { AccountEto } from 'src/app/usermanagement/shared/to/AccountEto';
import { PermissionEto } from 'src/app/usermanagement/shared/to/PermissionEto';
import { RoleEto } from 'src/app/usermanagement/shared/to/RoleEto';
import { UserEto } from 'src/app/usermanagement/shared/to/UserEto';
import { MediaType } from '../shared/enum/MediaType';
import { OrderStatus } from '../shared/enum/OrderStatus';
import { AddressEto } from '../shared/to/AddressEto';

const BASIC_PERM: PermissionEto[] = [{
  name: ApplicationPermissions.AUTH_USER,
  description: "Basic permissions"
}]

const ROLE1: RoleEto = {
  name: "User",
  description: "Description of normal user",
  permissions: BASIC_PERM
}

const ACCOUNT2: AccountEto = {
  id: 2,
  username: "test2",
  password: "dsf",
  email: "test2@test.com",
  isActivated: true
}

const USER: UserEto = {
  id: 1,
  name: "Tom",
  surname: "Willman",
  account: ACCOUNT2,
  role: ROLE1
}

const COORDINATOR: UserEto = {
  id: 2,
  name: "John",
  surname: "Smith",
  account: null,
  role: null
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

const ORDER = { orderNumber: "INVIU0001", coordinator: COORDINATOR, user: USER, status: OrderStatus.NEW,
 booking: {id: 1, name: "Booking #1", description: "short description", service: SERVICE, address: ADDRESS, user: USER, isConfirmed: true, predictedPrice: 1000, start: "22-11-2020", end: "20-11-2020", modificationDate: "22-11-2020", priceIndicatorList: null},
  price: 1000, createdAt: "22-11-2020" }

  const COMMENTS = [{
    id: 1,
    orderNumber: "Sd",
    content: "Mr Sgsd adsgfsdop sdgosd jopsdg opsdgpojdog sdpogjdsdsg sdopgjsdop jsd ospdgjf sspog jspo gso jgdp sdogpfj. sgojps gj. sgjopdfjg",
    user: USER,
    createdAt: "22-11-2020"
  },
  { 
    id: 2,
    orderNumber: "Sd",
    content: "Mr Sgsd adsgfsdop sdgosd jopsdg opsdgpojdog sdpogjdsdsg sdopgjsdodsfds sdf sdf sdf sd re yrthj ykyiuy lki7yolul iu tyr grdfgfd tt tyhrt p jsd ospdgjf sspog jspo gso jgdp sdogpfj. sgojps gj. sgjopdfjg",
    user: COORDINATOR,
    createdAt: "22-11-2020"
  }
]

const MEDIA_CONTENT = [{
  id: 2,
  type: MediaType.IMAGE,
  url: "url",
  orderNumber: "SAd"
},
{
  id: 2,
  type: MediaType.VIDEO,
  url: "urlasdasasdasasdasdassa",
  orderNumber: "SAd"
}]

@Component({
  selector: 'cf-order-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.scss']
})
export class OrderDetailsComponent implements OnInit {
  displayedColumns: string[] = ['type', 'name', 'actions'];
  dataSource = new MatTableDataSource(MEDIA_CONTENT);
  isSpinnerDisplayed = false;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  subscritpion: Subscription = new Subscription();
  order = ORDER;

  @Input()
  public orderControl: FormControl;
  public comments = COMMENTS;

  constructor(private translate: TranslateService, private router: Router, private gallery: Gallery, private route: ActivatedRoute) {
   }

  ngOnInit(): void {
    this.orderControl = new FormControl(ORDER);
    this.route.snapshot.paramMap.get('id');
  }

  ngOnDestroy() {
    this.subscritpion.unsubscribe();
  }

  navigateToBookingDetails(id: number){
    let currentHeadLink = this.router.url.substring(0,this.router.url.indexOf("o"));
    
    this.router.navigateByUrl(currentHeadLink + "orders/booking/details/" + id.toFixed());
  }

  showGallery(index: number) {
    let prop = {
        images: [
            {path: '../../../assets/img6.jpg'},
            {path: '../../../assets/img6.jpg'},
            {path: '../../../assets/img6.jpg'}
        ],
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
