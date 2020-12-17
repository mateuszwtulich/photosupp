import { Pipe, PipeTransform } from "@angular/core";

@Pipe({ name: 'translate' })
export class MockTranslatePipe implements PipeTransform {
    transform(_key: string, _module: string): any {
        return 'stub';
    }
}