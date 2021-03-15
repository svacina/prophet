import React, { useEffect } from 'react';
import { dispatch, useGlobalState } from './appState';

const EffectsRegister = () => {
    const [global] = useGlobalState('global');
    useEffect(
        () => {


        },
        [global],
    );

    return(
        <></>
    );
}

export default EffectsRegister;


