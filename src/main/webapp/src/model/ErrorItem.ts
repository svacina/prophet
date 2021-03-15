/**
 * Detailed server error
 */
export class ErrorItem {
    code: string;
    initialized: boolean = false;
    active: boolean = false;
    error: string;

    constructor(code, error){
        this.code = code
        this.error = error
    }

}
