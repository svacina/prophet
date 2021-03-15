import React from "react";
import LoadingOverlay from 'react-loading-overlay';
import {useGlobalState} from "../state/appState";
import prophetStyles from "../prophetStyles";


const LoadingIndicator = () => {

    const [loading] = useGlobalState('loading');
    const classes = prophetStyles();

    return (
        <React.Fragment>
            {loading && <LoadingOverlay
                active={loading}
                spinner
                className={classes.overlay}
                text='Loading data'>
                <p>

                </p>
            </LoadingOverlay> }
        </React.Fragment>
    )
}

export default LoadingIndicator;
