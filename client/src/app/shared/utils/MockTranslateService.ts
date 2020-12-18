import { of, Observable } from 'rxjs';

export class MockTranslateService {
    onLangChange: Observable<string> = of('stub');
    public get(_key: any): Observable<string> {
        return of('stub');
    }
    public instant(_key: string): string {
        return "stub";
    }

    public use(_key: string): Observable<string> {
        return of('stub');
    }
}