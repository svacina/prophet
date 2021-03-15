import React, {useEffect} from "react";
import prophetStyles from "../../../prophetStyles";
import AppSetup from "./AppSetup";
import AppData from "./AppData";
import EffectsRegister from "../../../state/EffectsRegister";
import LoadingIndicator from "../../../shared/LoadingIndicator";

const ProphetApp = () => {
    const classes = prophetStyles();
    return (
        <div>
            <div className={classes.content}>
                <EffectsRegister/>
                <LoadingIndicator/>
                <AppSetup></AppSetup>
                <AppData></AppData>
            </div>
        </div>
    )
}
export default ProphetApp;