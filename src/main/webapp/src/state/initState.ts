import {State} from "./state";
import {defaultState} from "./defaultState";

export const LOCAL_STORAGE_KEY = "prophetkey2020";
export const parseState = (str: string | null): State | null => {
    try {
        const state = JSON.parse(str || "");
        return state as State;
    } catch (e) {
        return null;
    }
};

const stateFromStorage = parseState(localStorage.getItem(LOCAL_STORAGE_KEY));

export const initialState: State = stateFromStorage || defaultState;
