import React from "react";
import PrettyPrintJson from "../../utils/PrettyPrintJson";
import CloneLink from "./CloneLink";
import {Mermaid} from "../../../components/canvas/Mermaid";
import {Typography} from "@material-ui/core";
import CloneDetail from "./CloneDetail";
import {createStyles, makeStyles, Theme} from "@material-ui/core/styles";

type Props = {
    clone: any;
    index: any;
}


const Clone = (props: Props) => {

    const diagram = "graph TD;\n" +
        "    PreserveController-- preserve ---PreserveService;\n" +
        "    PreserveService-->PreserveRepository;";
    return (
        <div>
            {/*<CloneLink link={props.clone.msController.msId.path} heading={"h5"}/>*/}

            {/*<div style={{overflow: 'auto'}}>*/}
            {/*    <Mermaid chart={diagram} index={props.index}/>*/}
            {/*</div>*/}
            <CloneDetail clone={props.clone} />
        </div>
    );
}
export default Clone;