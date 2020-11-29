import { BookingEto } from 'src/app/order/shared/to/BookingEto';

export class CalendarEvent {
    groupId: string;
    id: string;
    title: string;
    start: string;
    end: string;
    color: string;
    textColor: string;

    constructor(booking:  Partial<BookingEto> = {}){
        this.groupId = booking.userEto.id.toString();
        this.id = booking.id.toString();
        this.title = booking.name;
        this.start = booking.start;
        this.end = booking.end;
        this.textColor = 'white';
        this.color = '#86a3b7';
    }
}