import React from "react";
import {Global} from "../../model/Global";
import {Mermaid} from "./Mermaid";
import {Card} from '@material-ui/core';
import { CardContent } from '@material-ui/core';
import Typography from '@material-ui/core/Typography';

type Props = {
    global: Global;
}

/**
 * ToDo: Vincent, create frames for components, alignments, etc.
 * Displays communication and context map diagrams if present
 * otherwise display error notification
 * @param props: Global object from state
 * @stateless
 */
const GlobalDiagrams = (props: Props) => {

    const communication = (
        <>
            <Card style={{marginBottom: '20px'}}>
                <CardContent >
                    <Typography component="h4" variant="h4">
                        Communication Diagram
                    </Typography>
                    <div style={{overflow: 'auto'}}>
                        <Mermaid chart={props.global.communication} index={0}/>
                    </div>
                </CardContent>
            </Card>
        </>
    );

    const contextMap = (
        <>
            <Card style={{marginBottom: '20px'}}>
                <CardContent>
                    <Typography component="h4" variant="h4">
                        Context Map
                    </Typography>
                    <div style={{overflow: 'auto'}}>
                        <Mermaid chart={props.global.contextMap} index={1}/>
                    </div>
                </CardContent>
            </Card>
        </>
    );

    return (
        <React.Fragment>
            {communication}
            {contextMap}
        </React.Fragment>
    )
}
export default GlobalDiagrams;
