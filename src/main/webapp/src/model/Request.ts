import {Repository} from "./Repository";

/**
 * Request Body object for backend call
 */
export class Request {
    systemName: string;
    repositories: Repository[];
}
