import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort, Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { TranslateService } from '@ngx-translate/core';
import { SortUtil } from 'src/app/shared/utils/SortUtil';
import { IndicatorEto } from '../to/IndicatorEto';

const INDICATORS = [{
  id: 3,
  name: "Odległość od Częstochowy",
  description: "Proszę podać liczbę kilometrów Państwa lokalizacji od Częstochowy",
  locale: "pl",
  baseAmount: 20,
  doublePrice: 20
},
{
  id: 4,
  name: "Distance from Czestochowa",
  description: "Kindly provide number of kilometers to your localization from Czestochowa",
  locale: "en",
  baseAmount: 20,
  doublePrice: 20
},
{
  id: 1,
  name: "Szacowna liczba zdjęć",
  description: "Dla foto takiej proponujemy taką liczbę itp",
  locale: "pl",
  baseAmount: 50,
  doublePrice: 200
},
{
id: 2,
name: "Predicted number of photos",
description: "For this kind of service we propose the number",
locale: "en",
baseAmount: 50,
doublePrice: 200
},
{
  id: 5,
  name: "Szacowna liczba filmów",
  description: "Dla filmu takiego proponujemy taką liczbę filmów",
  locale: "pl",
  baseAmount: 1,
  doublePrice: 150
},
{
id: 6,
name: "Predicted number of clips",
description: "For this kind of service we propose the number",
locale: "en",
baseAmount: 1,
doublePrice: 150
},
{
id: 7,
name: "Szacowna liczba minut dla filmu",
description: "Dla filmu takiego typu proponujemy taką liczbę minut",
locale: "pl",
baseAmount: 2,
doublePrice: 40
},
{
id: 8,
name: "Predicted number of minutes for each clip",
description: "For this kind of service we propose the number",
locale: "en",
baseAmount: 2,
doublePrice: 40
}
]


@Component({
  selector: 'cf-indicators-overview',
  templateUrl: './indicators-overview.component.html',
  styleUrls: ['./indicators-overview.component.scss']
})
export class IndicatorsOverviewComponent implements OnInit {
  displayedColumns: string[] = ['name', 'description', 'baseAmount', 'doublePrice', 'language', 'actions'];
  dataSource = new MatTableDataSource(INDICATORS);
  isSpinnerDisplayed = false;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private translate: TranslateService) { }

  ngOnInit(): void {
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
    this.dataSource.filterPredicate = this.prepareFilterPredicate();
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  private prepareFilterPredicate(): (data: IndicatorEto, filter: string) => boolean {
    return (data: IndicatorEto, filter: string) => {

      return data.name.toLocaleLowerCase().includes(filter) || data.description.toLocaleLowerCase().includes(filter) || 
      data.baseAmount.toFixed().includes(filter) || data.doublePrice.toFixed().includes(filter) ||
        this.translate.instant("table." + data.locale).toLocaleLowerCase().includes(filter);
    };
  }

  filterStatus(status: string) {
    if (status != "ALL") {
      this.dataSource.filter = this.translate.instant("table." + status).trim().toLowerCase();
    } else {
      this.dataSource.filter = "";
    }
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
          return SortUtil.compare(a.name, b.name, isAsc);
        case "description":
          return SortUtil.compare(a.description, b.description, isAsc);
        case "language":
          return SortUtil.compare(this.translate.instant("services." + a.locale), this.translate.instant("services." + b.locale), isAsc);
        case "baseAmount":
          return SortUtil.compare(a.baseAmount, b.baseAmount, isAsc);
        case "doublePrice":
          return SortUtil.compare(a.doublePrice, b.doublePrice, isAsc);
        default:
          return 0;
      }
    });
  }
}
