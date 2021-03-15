import {Ms} from "../model/Ms";
import {Global} from "../model/Global";

export type Action =
    | { type: "setGithubUrl", githubUrl: string }
    | { type: "setGlobal", global: Global }
    | { type: "setMs", ms: Ms[] }
    | { type: "startLoading"}
    | { type: "stopLoading"}
    | { type: "hideGitError"}
    | { type: "showGitError"};
