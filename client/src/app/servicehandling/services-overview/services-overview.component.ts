import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort, Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { TranslateService } from '@ngx-translate/core';
import { IndicatorEto } from 'src/app/servicehandling/to/IndicatorEto';
import { ServiceEto } from 'src/app/servicehandling/to/ServiceEto';
import { SortUtil } from 'src/app/shared/utils/SortUtil';

const fuelIndicatorPL = {
  id: 3,
  name: "Odległość od Częstochowy",
  description: "Proszę podać liczbę kilometrów Państwa lokalizacji od Częstochowy",
  locale: "pl",
  baseAmount: 20,
  doublePrice: 20
}

const fuelIndicatorEN = {
  id: 4,
  name: "Distance from Czestochowa",
  description: "Kindly provide number of kilometers to your localization from Czestochowa",
  locale: "en",
  baseAmount: 20,
  doublePrice: 20
}

const fotoIndicatorsPL: IndicatorEto[] = [{
  id: 1,
  name: "Szacowna liczba zdjęć",
  description: "Dla foto takiej proponujemy taką liczbę itp",
  locale: "pl",
  baseAmount: 50,
  doublePrice: 200
},
fuelIndicatorPL
]

const fotoIndicatorsEN: IndicatorEto[] = [{
id: 2,
name: "Predicted number of photos",
description: "For this kind of service we propose the number",
locale: "en",
baseAmount: 50,
doublePrice: 200
},
fuelIndicatorEN
]

const filmIndicatorsPL: IndicatorEto[] = [{
  id: 5,
  name: "Szacowna liczba filmów",
  description: "Dla filmu takiego proponujemy taką liczbę filmów",
  locale: "pl",
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
  }]

const filmIndicatorsEN: IndicatorEto[] = [{
id: 6,
name: "Predicted number of clips",
description: "For this kind of service we propose the number",
locale: "en",
baseAmount: 1,
doublePrice: 150
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

const SERVICES: ServiceEto[] = [{
  id: 1,
  name: "foto",
  description: "opis",
  locale: "pl",
  basePrice: 300,
  indicatorEtoList: fotoIndicatorsPL
},
{
id: 2,
name: "Photo",
description: "Description",
locale: "en",
basePrice: 300,
indicatorEtoList: fotoIndicatorsEN
},
{
id: 3,
name: "film",
description: "opis filmu",
locale: "pl",
basePrice: 600,
indicatorEtoList: filmIndicatorsPL
},
{
id: 4,
name: "Film",
description: "Description",
locale: "en",
basePrice: 600,
indicatorEtoList: filmIndicatorsEN
}];

@Component({
  selector: 'cf-services-overview',
  templateUrl: './services-overview.component.html',
  styleUrls: ['./services-overview.component.scss']
})

export class ServicesOverviewComponent implements OnInit {
  displayedColumns: string[] = ['name', 'locale', 'basePrice', 'indicators', 'actions'];
  dataSource = new MatTableDataSource(SERVICES);
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

  private prepareFilterPredicate(): (data: ServiceEto, filter: string) => boolean {
    return (data: ServiceEto, filter: string) => {
      let inIndicators: boolean = !!data.indicatorEtoList.find(indicator => indicator.name.toLocaleLowerCase().includes(filter));

      return data.name.toLocaleLowerCase().includes(filter) || inIndicators ||
        this.translate.instant("table." + data.locale).toLocaleLowerCase().includes(filter) || data.basePrice.toFixed().includes(filter);
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
        case "locale":
          return SortUtil.compare(this.translate.instant("services." + a.locale), this.translate.instant("services." + b.locale), isAsc);
        case "basePrice":
          return SortUtil.compare(a.basePrice, b.basePrice, isAsc);
        case "indicators":
          return SortUtil.compare(a.indicatorEtoList[0].name, b.indicatorEtoList[1].name, isAsc);
        default:
          return 0;
      }
    });
  }
}
