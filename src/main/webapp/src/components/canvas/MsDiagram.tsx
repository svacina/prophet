import React from "react";
import {Mermaid} from "./Mermaid";
import {Ms} from "../../model/Ms";
import {Card} from '@material-ui/core';
import { CardContent } from '@material-ui/core';
import Typography from '@material-ui/core/Typography';
// import mermaid from "mermaid";
//
// mermaid.initialize({
//     startOnLoad: true,
//
// });

type Props = {
    ms: Ms;
    index: number;
}

/**
 * ToDo: Vincent, create frames for components, alignments, etc.
 * @param props
 * @constructor
 */
const MsDiagram = (props: Props) => {

    const displayNotJava = props.ms.notJava;
    const displayNoBoundedContext = props.ms.noBoundedContext;

    const name = (
      <>
          {props.ms.name}
      </>
    );

    const notJava = (
        <>
            <p>
                {'Microservice does not support Spring Annotations, more: '}
                <a href="https://github.com/cloudhubs/prophet-web/wiki/Prophet-Language-Support" target="_blank">Prophet-Language-Support</a>
            </p>

        </>
    )

    const noBoundedContext = (
        <>
            <p>
                {'Microservice does not have model, more: '}
                <a href="https://github.com/cloudhubs/prophet-web/wiki/Prophet-Data-Model-Support" target="_blank">Prophet-Data-Model-Support</a>
            </p>
        </>
    )

    const boundedContext = (
        <>
            <Mermaid chart={props.ms.boundedContext} index={props.index}/>
        </>
    );

    return (
        <React.Fragment>
            <Card style={{marginBottom: '20px'}}>
                <CardContent>
                    <Typography component="h4" variant="h4">
                        {name}
                    </Typography>
                    <div style={{overflow: 'auto'}}>
                        {displayNotJava ? notJava : <></> }
                        {displayNoBoundedContext ? noBoundedContext : <></> }
                        {!props.ms.notJava && !props.ms.noBoundedContext && boundedContext}
                    </div>
                </CardContent>
            </Card>
        </React.Fragment>
    )
}
export default MsDiagram;
