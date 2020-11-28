import { PermissionEto } from './PermissionEto';

export class RoleEto {
    id: number;
    name: string;
    description: string;
    permissions: PermissionEto[];
}