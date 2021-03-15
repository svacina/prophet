import React from "react";
import EntityCluster from "./EntityCluster";
import Paper from "@material-ui/core/Paper";
import prophetStyles from "../../../prophetStyles";

const InconsistencyList = ({inconsistencyList}) => {
    const classes = prophetStyles();
    return (
        <React.Fragment>
            {inconsistencyList && inconsistencyList.map((n) => (
                <>
                    { (n.hasMissingFiledAnnotations || n.hasMissingField) &&
                    <>
                        <div className={classes.boxes_root}>
                            <Paper className={classes.boxes_paper}>
                                <EntityCluster entityCluster={n}/>
                            </Paper>
                        </div>
                    </>
                    }
                </>
            ))}
        </React.Fragment>
    )
}
export default InconsistencyList;