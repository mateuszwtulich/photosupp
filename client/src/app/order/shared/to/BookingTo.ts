import { PriceIndicatorTo } from 'src/app/core/to/PriceIndicatorTo';
import { AddressTo } from './AddressTo';

export class BookingTo{
    id: number;
    name: string;
    description: string;
    serviceId: number;
    address: AddressTo;
    userId: number;
    predictedPrice: number;
    start: string;
    end: string;
    modificationDate: string;
    priceIndicatorList: PriceIndicatorTo[];
}