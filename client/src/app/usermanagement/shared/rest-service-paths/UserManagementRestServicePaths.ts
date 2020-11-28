import { BackendApiServicePath } from 'src/app/pricing/rest-service-paths/BackendApiServicePath';

export class UserManagementRestServicePaths{

    public static FIND_ALL_USERS() {
        return BackendApiServicePath.BACKEND_API() + 'user/v1/users';
    }

    public static USER_PATH() {
        return BackendApiServicePath.BACKEND_API() + 'user/v1/user';
    }

    public static FIND_ALL_ACCOUNTS() {
        return BackendApiServicePath.BACKEND_API() + 'user/v1/users/accounts';
    }

    public static FIND_ALL_USERS_BY_ROLE() {
        return BackendApiServicePath.BACKEND_API() + 'user/v1/users/role/';
    }

    public static ACCOUNT_PATH() {
        return BackendApiServicePath.BACKEND_API() + 'user/v1/user/{id}/account';
    }

    public static ROLE_PATH() {
        return BackendApiServicePath.BACKEND_API() + 'user/v1/role';
    }

    public static FIND_ALL_ROLES_PATH() {
        return BackendApiServicePath.BACKEND_API() + 'user/v1/roles';
    }
}