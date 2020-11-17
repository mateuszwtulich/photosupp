import {MediaMatcher} from '@angular/cdk/layout';
import {ChangeDetectorRef, Component, OnDestroy} from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { SidenavTo } from '../to/SidenavTo';

@Component({
  selector: 'cf-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnDestroy {
  mobileQuery: MediaQueryList;
  filterNav: SidenavTo[];

  homeNav = [ 
    new SidenavTo("/login", "login", null),
    new SidenavTo("/home", "account_box", null),
    new SidenavTo("/home/calculate", "calculate", null)
  ];

  private _mobileQueryListener: () => void;

  constructor(changeDetectorRef: ChangeDetectorRef, media: MediaMatcher, private translate: TranslateService) {
    this.mobileQuery = media.matchMedia('(max-width: 600px)');
    this._mobileQueryListener = () => changeDetectorRef.detectChanges();
    this.mobileQuery.addListener(this._mobileQueryListener);
  }

  ngOnInit(): void {
    this.homeNav.forEach(nav => {
      nav.text = this.translate.instant('sidenav.' + nav.icon);
    })

    this.filterNav = this.homeNav;
  }

  ngOnDestroy(): void {
    this.mobileQuery.removeListener(this._mobileQueryListener);
  }
}





/**  Copyright 2020 Google LLC. All Rights Reserved.
    Use of this source code is governed by an MIT-style license that
    can be found in the LICENSE file at http://angular.io/license */