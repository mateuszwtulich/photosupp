import {MediaMatcher} from '@angular/cdk/layout';
import {ChangeDetectorRef, Component, OnDestroy} from '@angular/core';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { Subscription } from 'rxjs';
import { SidenavTo } from '../to/SidenavTo';

@Component({
  selector: 'cf-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnDestroy {
  mobileQuery: MediaQueryList;
  filterNav: SidenavTo[];
  languages: string[];
  langDefault: string[] = ["pl", "en"];
  subscription: Subscription = new Subscription();

  homeNav = [ 
    new SidenavTo("calculate", "calculate", null),
    new SidenavTo("/home", "account_box", null),
    new SidenavTo("/login", "login", null)
  ];

  clientNav = [
    new SidenavTo("client", "home", null),
    new SidenavTo("client/order/planning", "book_online", null),
    new SidenavTo("client/orders", "list_alt", null),
    new SidenavTo("client/scheduler", "calendar_today", null),
    new SidenavTo("client/user/details", "account_circle", null),
    new SidenavTo("home", "power_settings_new", null)
  ];

  managerNav = [
    new SidenavTo("manager", "home", null),
    new SidenavTo("manager/orders", "list_alt", null),
    new SidenavTo("manager/services", "design_services", null),
    new SidenavTo("manager/scheduler", "calendar_today", null),
    new SidenavTo("manager/user/details", "account_circle", null),
    new SidenavTo("manager/user/overview", "supervised_user_circle", null),
    new SidenavTo("home", "power_settings_new", null)
  ]

  private _mobileQueryListener: () => void;

  constructor(changeDetectorRef: ChangeDetectorRef, media: MediaMatcher, private translate: TranslateService, private router: Router) {
    this.mobileQuery = media.matchMedia('(max-width: 600px)');
    this._mobileQueryListener = () => changeDetectorRef.detectChanges();
    this.mobileQuery.addListener(this._mobileQueryListener);
  }

  ngOnInit(): void {
    if(window.location.pathname.startsWith("/client")){
      this.filterNav = this.clientNav;
    } else if(window.location.pathname.startsWith("/manager")){
      this.filterNav = this.managerNav;
    } else {
      this.filterNav = this.homeNav;
    }

    this.refreshSidenavText();
  }

  navigateToHome() {
    if(window.location.pathname.startsWith("/client")){
      this.navigate("/client");
    } else if(window.location.pathname.startsWith("/manager")){
      this.navigate("/manager");
    } else {
      this.navigate("/home");
      this.filterNav = this.homeNav;
      this.refreshSidenavText();
    }
  }

  ngOnDestroy(): void {
    this.mobileQuery.removeListener(this._mobileQueryListener);
    this.subscription.unsubscribe();
  }

  changeLanguage(lang: string){
    this.subscription.add(this.translate.use(lang).subscribe(() => {
      this.languages = this.langDefault;
      this.refreshSidenavText();
    }));
  }
  
  refreshSidenavText(): void {
    this.filterNav.forEach(nav => {
      nav.text = this.translate.instant('sidenav.' + nav.icon);
    });

    this.languages = this.langDefault.filter(lang => lang != this.translate.currentLang);
  }

  navigate(url: string) {
    this.router.navigateByUrl(url);
  }
}





/**  Copyright 2020 Google LLC. All Rights Reserved.
    Use of this source code is governed by an MIT-style license that
    can be found in the LICENSE file at http://angular.io/license */