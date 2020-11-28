import { HttpClient, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApplicationPermission } from '../core/utils/ApplicationPermission';
import { LocalStorageService } from './services/localStorage.service';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

    constructor(private http: HttpClient, private localStorageService: LocalStorageService) {
    }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        let jsonRequest: HttpRequest<any> = null;
        if (this.localStorageService.isStorageInitialized()) {
            const scopeInfo: ScopePermissionInfo = {
                userId: this.localStorageService.getAuthInfo().userId,
                isAdmin: this.localStorageService.getAuthorities().includes(ApplicationPermission.A_CRUD_SUPER) ? true : false
            };

            jsonRequest = request.clone({
                setHeaders: {
                    Authorization: `Bearer ${this.localStorageService.getToken()}`,
                    Scope: JSON.stringify(scopeInfo)
                }
            }
            );
        } else {
            jsonRequest = request.clone({
                setHeaders: {
                    Authorization: `Bearer `
                }
            }
            );
        }
        return next.handle(jsonRequest);
    }
}

export interface ScopePermissionInfo {
    userId: number;
    isAdmin: boolean;
}
