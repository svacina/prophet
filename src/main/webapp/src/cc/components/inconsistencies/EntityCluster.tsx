import React from "react";
import Typography from '@material-ui/core/Typography';
import Entity from "./Entity";
import Grid from "@material-ui/core/Grid";

const EntityCluster = ({entityCluster}) => {
    return (
        <React.Fragment>

            <Typography variant="h4" gutterBottom>
                {entityCluster.name}
            </Typography>

            <Grid container spacing={3}>
                {entityCluster && entityCluster.msEntities.map((n) => (
                    <Entity entity={n} />
                ))}
            </Grid>

        </React.Fragment>
    )
}
export default EntityCluster;