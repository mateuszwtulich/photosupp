import {MediaMatcher} from '@angular/cdk/layout';
import {ChangeDetectorRef, Component, OnDestroy} from '@angular/core';
import { Router } from '@angular/router';
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
  languages: string[];
  langDefault: string[] = ["pl", "en"];

  homeNav = [ 
    new SidenavTo("calculate", "calculate", null),
    new SidenavTo("/home", "account_box", null),
    new SidenavTo("/login", "login", null)
  ];

  private _mobileQueryListener: () => void;

  constructor(changeDetectorRef: ChangeDetectorRef, media: MediaMatcher, private translate: TranslateService, private router: Router) {
    this.mobileQuery = media.matchMedia('(max-width: 600px)');
    this._mobileQueryListener = () => changeDetectorRef.detectChanges();
    this.mobileQuery.addListener(this._mobileQueryListener);
  }

  ngOnInit(): void {
    this.filterNav = this.homeNav;

    this.refreshSidenavText();
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

  navigateToHome() {
    this.router.navigateByUrl("/home");
  }

  ngOnDestroy(): void {
    this.mobileQuery.removeListener(this._mobileQueryListener);
  }

  changeLanguage(lang: string){
    this.translate.use(lang).subscribe(() => {
      this.languages = this.langDefault;
      this.refreshSidenavText();
    });
  }
}





/**  Copyright 2020 Google LLC. All Rights Reserved.
    Use of this source code is governed by an MIT-style license that
    can be found in the LICENSE file at http://angular.io/license */