import {dispatch} from "./appState";
import {Global} from "../model/Global";
import {Ms} from "../model/Ms";

const ActionsRegister = {
    setGithubUrl(githubUrl: string) {
        dispatch({
            githubUrl: githubUrl,
            type: 'setGithubUrl',
        })
    },
    setProphetResponse(global: Global, ms: Ms[]) {
        dispatch({
            global: global,
            type: 'setGlobal',
        });
        dispatch({
            ms: ms,
            type: 'setMs',
        });
    },
    startLoading() {
        dispatch({
            type: 'startLoading',
        })
    },
    stopLoading() {
        dispatch({
            type: 'stopLoading',
        })
    },
    hideGitError() {
        dispatch({
            type: 'hideGitError',
        })
    },
    showGitError() {
        dispatch({
            type: 'showGitError',
        })
    },
}

export default ActionsRegister;
