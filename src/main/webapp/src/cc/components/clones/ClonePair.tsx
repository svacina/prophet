import React from "react";
import Clone from "./Clone";
import CloneSummary from "./CloneSummary";
import {Button, Card, Typography} from "@material-ui/core";
import { makeStyles, createStyles, Theme } from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import Grid from '@material-ui/core/Grid';
import ArrowForwardIosIcon from '@material-ui/icons/ArrowForwardIos';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';
import Accordion from "@material-ui/core/Accordion";
import AccordionSummary from "@material-ui/core/AccordionSummary";
import AccordionDetails from "@material-ui/core/AccordionDetails";


type Props = {
    clonePair: any;
}

const useStyles = makeStyles((theme: Theme) =>
    createStyles({
        root: {
            flexGrow: 1,
        },
        paper: {
            padding: theme.spacing(2),
            // color: theme.palette.text.secondary,
        },
    }),
);

/**
 * <b>{props.clonePair.id}</b>
 * @param props
 * @constructor
 */
const ClonePair = (props: Props) => {
    const classes = useStyles();
    const [showResults, setShowResults] = React.useState(true)
    const onClick = (e) => {
        e.preventDefault();
        if (showResults) {
            setShowResults(false);
        } else  {
            setShowResults(true);
        }

    }
    return (
        <div>
             <Card>
                <div className={classes.root}>
                    <Paper className={classes.paper}>
                        <Accordion expanded={showResults} onClick={onClick}>
                            <AccordionSummary
                                expandIcon={<ExpandMoreIcon />}
                                aria-controls="panel1a-content"
                                id="panel1a-header">
                                <Typography>
                                    <b>{props.clonePair.typeA && "A"}</b> <b>{props.clonePair.typeB && "B"}</b>  {props.clonePair.a.msController.packageName}.{props.clonePair.a.msController.className} -> {props.clonePair.b.msController.packageName}.{props.clonePair.b.msController.className}
                                </Typography>
                            </AccordionSummary>
                            <AccordionDetails>

                                <Grid container spacing={3}>
                                    <Grid item xs={4}>
                                        <CloneSummary
                                            similarityController={props.clonePair.similarityController}
                                            similarityService={props.clonePair.similarityService}
                                            similarityRepository={props.clonePair.similarityRepository}
                                            similarityRestCalls={props.clonePair.similarityRestCalls}
                                            globalSimilarity={props.clonePair.globalSimilarity}
                                            typeA={props.clonePair.typeA}
                                            typeB={props.clonePair.typeB} />
                                    </Grid>
                                    <Grid item xs={8}>
                                        <Grid container justify="flex-end">
                                            {/*<Button onClick={e => onClick(e)}>Solve</Button>*/}
                                        </Grid>
                                    </Grid>
                                    <Grid item xs={6}>
                                        <Paper className={classes.paper}>
                                            <Clone clone={props.clonePair.a} index={1} />
                                        </Paper>
                                    </Grid>
                                    <Grid item xs={6}>
                                        <Paper className={classes.paper}>
                                            <Clone clone={props.clonePair.b} index={2} />
                                        </Paper>
                                    </Grid>
                                </Grid>

                            </AccordionDetails>
                        </Accordion>


                    </Paper>
                </div>


            </Card>
        </div>
    );
}
export default ClonePair;