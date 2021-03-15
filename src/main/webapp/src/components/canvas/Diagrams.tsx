import React from "react";
import {useGlobalState} from "../../state/appState";
import GlobalDiagrams from "./GlobalDiagrams";
import MsDiagram from "./MsDiagram";
import {Ms} from "../../model/Ms";

/**
 * Passes global and microservice variables from state to stateless
 * components
 * @variables Global and Ms object from State
 * @stateful
 * @constructor
 *
 */
const Diagrams = () => {

    const [global] = useGlobalState('global');
    const [ms] = useGlobalState('ms');
    const [gitError] = useGlobalState('gitError');

    return (
        <React.Fragment>
            {!gitError &&
                <>
                    <GlobalDiagrams global={global}/>
                    {ms.map((m: Ms, i: number) => (<MsDiagram ms={m} index={i+2}/>))}
                </>
            }

        </React.Fragment>
    )
}
export default Diagrams;
