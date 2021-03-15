import { Dispatch } from "react";
import { createStore } from "react-hooks-global-state";
import { applyMiddleware } from "redux";
import {State} from "./state";
import { Action } from "./action";
import {initialState, LOCAL_STORAGE_KEY} from "./initState";
import {reducer} from "./reducer";

const saveStateToStorage = ({ getState }: { getState: () => State }) => (
    next: Dispatch<Action>
) => (action: Action) => {
    const returnValue = next(action);
    localStorage.setItem(LOCAL_STORAGE_KEY, JSON.stringify(getState()));
    return returnValue;
};

export const { dispatch, useGlobalState } = createStore(
    reducer,
    initialState,
    applyMiddleware(saveStateToStorage)
);


