import { Component, OnInit, ViewChild } from '@angular/core';
import { CalendarOptions, FullCalendarComponent } from '@fullcalendar/angular';
import { LangChangeEvent, TranslateService } from '@ngx-translate/core';
import { Subscription } from 'rxjs';
import { CalendarEvent } from '../to/CalendarEvent';
import { Router } from '@angular/router';
import { SchedulerService } from '../services/scheduler.service';
import { BookingEto } from 'src/app/order/shared/to/BookingEto';

const EVENTS = [
{
  id: 1,
    type: "Order",
    title: "zamówienie",
    start: "22-11-2020",
    end: "22-11-2020",
    color: "white",
    textColor: "black"
}
]

@Component({
  selector: 'cf-scheduler',
  templateUrl: './scheduler.component.html',
  styleUrls: ['./scheduler.component.scss']
})
export class SchedulerComponent implements OnInit {
  isSpinnerDisplayed = false;
  calendarEvents: CalendarEvent[];
  textColor: string;
  subscritpion: Subscription = new Subscription();

  calendarOptions: CalendarOptions = {
    eventClick: this.eventClicked.bind(this),
    initialView: 'dayGridMonth',
    selectable: true,
    select: this.selectedFields.bind(this),
    unselectAuto: false,
    weekends: true,
    locale: "pl",
    firstDay: 1,
    aspectRatio: 1.75,
    eventTextColor: 'black',
    events: [
      {
        id: '1',
        type: 'Order',
        title: 'zamówienie',
        start: '2020-11-20',
        end: '2020-11-22',
        textColor: 'white',
        color: '#86a3b7',
      },
      {
        id: '2',
        type: 'Booking',
        title: 'rezerwacja',
        start: '2020-11-27',
        end: '2020-11-27',
        textColor: 'black',
        color: '#e9f0f3',      
      },
      {
        daysOfWeek: [0], //Sundays and saturdays
        rendering:"background",
        color: "#ff9f89",
        display: 'background',
        overLap: false,
        allDay: true
      }
    ]
  };

  @ViewChild('calendar') calendarComponent: FullCalendarComponent;
  constructor(private translate: TranslateService, private router: Router, private schedulerService: SchedulerService) { }

  ngOnInit(): void {
    this.calendarEvents = EVENTS;
    this.subscritpion.add(this.translate.onLangChange.subscribe((event: LangChangeEvent) => {
      this.calendarOptions.locale = event.lang;
    }));

    this.checkIfPlanning();
  }

  filterEvents(type: string){

  }

  private checkIfPlanning(){
    if(this.router.url!=='/client/order/planning'){
      this.calendarOptions.selectable = false;
    }
  }

  private selectedFields(info){
    let booking = new BookingEto();
    booking.start = info.startStr;
    booking.end = info.endStr;

    this.schedulerService.datesDataSource.next(booking);
    console.log(info.startStr + " " + info.endStr)
  }


  eventClicked(arg){
    console.log(arg.event.id)
  }
}
