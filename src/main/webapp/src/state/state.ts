import {Global} from "../model/Global";
import {Ms} from "../model/Ms";
import {Request} from "../model/Request";

/**
 * Global Application State
 */
export type State = {
    sutPath: string;
    sutError: boolean;
    idePath: string;
    ideError: boolean;
    ideExe: string;
    ideExeError: boolean;
    analyzedData: any;
    githubUrl: string;
    global: Global;
    ms: Ms[];
    request: Request;
    loading: boolean;
    gitError: boolean;
}
