import { PriceIndicatorEto } from 'src/app/core/to/PriceIndicatorEto';
import { ServiceEto } from 'src/app/core/to/ServiceEto';
import { UserEto } from 'src/app/usermanagement/shared/to/UserEto';
import { AddressEto } from './AddressEto';

export class BookingEto{
    name: string;
    description: string;
    service: ServiceEto;
    address: AddressEto;
    user: UserEto;
    isConfirmed: boolean;
    predictedPrice: number;
    start: string;
    end: string;
    modificationDate: string;
    priceIndicatorList: PriceIndicatorEto[];
}