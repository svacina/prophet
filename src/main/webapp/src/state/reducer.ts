import {Action} from "./action";
import {initialState} from "./initState";

/**
 * Prophet Web App Reducers
 * @param state: Initial state or current state
 * @param action: Given action
 */
export const reducer = (state = initialState, action: Action) => {
    switch (action.type) {
        case "setGithubUrl":
            return {
                ...state,
                githubUrl: action.githubUrl
            }
        case "setGlobal":
            return {
                ...state,
                global: action.global
            }
        case "setMs":
            return {
                ...state,
                ms: action.ms
            }
        case "startLoading":
            return {
                ...state,
                loading: true
            }
        case "stopLoading":
            return {
                ...state,
                loading: false
            }
        case "showGitError":
            return {
                ...state,
                gitError: true
            }
        case "hideGitError":
            return {
                ...state,
                gitError: false
            }
        default:
            return state;
    }
};
