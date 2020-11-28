import { BackendApiServicePath } from 'src/app/pricing/rest-service-paths/BackendApiServicePath';

export class ServiceHandlingRestServicePaths{

    public static FIND_ALL_SERVICES() {
        return BackendApiServicePath.BACKEND_API() + 'service/v1/services';
    }

    public static SERVICE_PATH() {
        return BackendApiServicePath.BACKEND_API() + 'service/v1/service';
    }

    public static SERVICE_PATH_BY_ID(serviceId: string) {
        return BackendApiServicePath.BACKEND_API() + 'service/v1/service/' + serviceId;
    }

    public static FIND_ALL_INDICATORS() {
        return BackendApiServicePath.BACKEND_API() + 'service/v1/indicators';
    }

    public static INDICATOR_PATH() {
        return BackendApiServicePath.BACKEND_API() + 'service/v1/indicator';
    }

    public static INDICATOR_PATH_BY_ID(indicatorId: string) {
        return BackendApiServicePath.BACKEND_API() + 'service/v1/indicator/' + indicatorId;
    }

    public static FIND_ALL_BOOKINGS() {
        return BackendApiServicePath.BACKEND_API() + 'service/v1/bookings';
    }

    public static BOOKING_PATH() {
        return BackendApiServicePath.BACKEND_API() + 'service/v1/booking';
    }

    public static BOOKING_PATH_BY_ID(bookingId: string) {
        return BackendApiServicePath.BACKEND_API() + 'service/v1/booking/' + bookingId;
    }

    public static FIND_ALL_BOOKINGS_BY_USER_ID(userId: string) {
        return BackendApiServicePath.BACKEND_API() + 'service/v1/bookings/' + userId;
    }

    public static FIND_ALL_CITIES() {
        return BackendApiServicePath.BACKEND_API() + 'service/v1/address/cities';
    }

    public static FIND_ALL_STREETS() {
        return BackendApiServicePath.BACKEND_API() + 'service/v1/address/streets';
    }

    public static ROLE_PATH() {
        return BackendApiServicePath.BACKEND_API() + 'user/v1/role';
    }

    public static FIND_ALL_ROLES_PATH() {
        return BackendApiServicePath.BACKEND_API() + 'user/v1/roles';
    }

    public static CONFIRM_BOOKING_PATH(bookingId: string) {
        return BackendApiServicePath.BACKEND_API() + 'service/v1/booking/' + bookingId + "/confirm";
    }
}