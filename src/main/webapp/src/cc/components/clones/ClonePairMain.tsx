import React from "react";
import Clone from "./Clone";
import CloneSummary from "./CloneSummary";
import {Box, Button, Card, Typography} from "@material-ui/core";
import { makeStyles, createStyles, Theme } from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import Grid from '@material-ui/core/Grid';
import ArrowForwardIosIcon from '@material-ui/icons/ArrowForwardIos';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';
import Accordion from "@material-ui/core/Accordion";
import AccordionSummary from "@material-ui/core/AccordionSummary";
import AccordionDetails from "@material-ui/core/AccordionDetails";
import TableContainer from "@material-ui/core/TableContainer";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableRow from "@material-ui/core/TableRow";
import TableCell from "@material-ui/core/TableCell";
import CloneLink from "./CloneLink";
import CodeSnippet from "../../utils/CodeSnippet";


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
const ClonePairMain = (props: Props) => {
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
                        <Accordion expanded={showResults} >
                            <AccordionSummary
                                expandIcon={<ExpandMoreIcon />}
                                aria-controls="panel1a-content"
                                id="panel1a-header">
                                <Typography onClick={onClick}>
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
                                    <Grid item xs={12}>
                                        <Paper className={classes.paper}>
                                            <div>

                                                {/* LINKS */}


                                                <Grid container spacing={3}>
                                                    <Grid item xs={6}>
                                                        <TableContainer component={Paper} className={classes.paper}>
                                                            <Table aria-label="simple table">
                                                                <TableBody>
                                                                    <TableRow key={1}>
                                                                        <TableCell component="th" scope="row">
                                                                            {"Controller"}
                                                                        </TableCell>
                                                                        <TableCell align="left">
                                                                            <CloneLink link={props.clonePair.a.msController.msId.path} heading={"p"}/> <span>({props.clonePair.a.msControllerMethod.line})</span>
                                                                        </TableCell>
                                                                    </TableRow>
                                                                    {props.clonePair.a.msService &&
                                                                    <TableRow key={2}>
                                                                        <TableCell component="th" scope="row">
                                                                            {"Service"}
                                                                        </TableCell>
                                                                        <TableCell align="left">
                                                                            {props.clonePair.a.msService && <CloneLink link={props.clonePair.a.msService.msId.path} heading={"p"}/>} <span>({props.clonePair.a.msServiceMethod.line})</span>
                                                                        </TableCell>
                                                                    </TableRow>
                                                                    }

                                                                    {props.clonePair.a.msRepository && props.clonePair.a.msRepositoryMethod &&
                                                                    <TableRow key={2}>
                                                                        <TableCell component="th" scope="row">
                                                                            {"Repository"}
                                                                        </TableCell>
                                                                        <TableCell align="left">
                                                                            {props.clonePair.a.msRepository && <CloneLink link={props.clonePair.a.msRepository.msId.path} heading={"p"}/>} <span>({props.clonePair.a.msRepositoryMethod.line})</span>
                                                                        </TableCell>
                                                                    </TableRow>
                                                                    }
                                                                </TableBody>
                                                            </Table>
                                                        </TableContainer>
                                                    </Grid>

                                                    <Grid item xs={6}>
                                                        <TableContainer component={Paper} className={classes.paper}>
                                                            <Table aria-label="simple table">
                                                                <TableBody>
                                                                    <TableRow key={1}>
                                                                        <TableCell component="th" scope="row">
                                                                            {"Controller"}
                                                                        </TableCell>
                                                                        <TableCell align="left">
                                                                            <CloneLink link={props.clonePair.b.msController.msId.path} heading={"p"}/> <span>({props.clonePair.b.msControllerMethod.line})</span>
                                                                        </TableCell>
                                                                    </TableRow>
                                                                    {props.clonePair.b.msService &&
                                                                    <TableRow key={2}>
                                                                        <TableCell component="th" scope="row">
                                                                            {"Service"}
                                                                        </TableCell>
                                                                        <TableCell align="left">
                                                                            {props.clonePair.b.msService && <CloneLink link={props.clonePair.b.msService.msId.path} heading={"p"}/>} <span>({props.clonePair.b.msServiceMethod.line})</span>
                                                                        </TableCell>
                                                                    </TableRow>
                                                                    }

                                                                    {props.clonePair.b.msRepository && props.clonePair.b.msRepositoryMethod &&
                                                                    <TableRow key={2}>
                                                                        <TableCell component="th" scope="row">
                                                                            {"Repository"}
                                                                        </TableCell>
                                                                        <TableCell align="left">
                                                                            {props.clonePair.b.msRepository && <CloneLink link={props.clonePair.b.msRepository.msId.path} heading={"p"}/>} <span>({props.clonePair.b.msRepositoryMethod.line})</span>
                                                                        </TableCell>
                                                                    </TableRow>
                                                                    }
                                                                </TableBody>
                                                            </Table>
                                                        </TableContainer>
                                                    </Grid>

                                                </Grid>


                                                {/* CONTROLLER */}

                                                <Grid container spacing={2}>
                                                    <Grid item xs={6}>
                                                        <TableContainer component={Paper} className={classes.paper}>
                                                            <Table aria-label="properties table">
                                                                <TableBody>
                                                                    <TableRow key={99}>
                                                                        <TableCell component="th" scope="row">
                                                                            <b>{"Controller"}</b>
                                                                        </TableCell>
                                                                        <TableCell align="left">

                                                                        </TableCell>
                                                                    </TableRow>
                                                                    <TableRow key={100}>
                                                                        <TableCell component="th" scope="row">
                                                                            {"Return Type"}
                                                                        </TableCell>
                                                                        <TableCell align="left">
                                                                            {props.clonePair.a.msControllerMethod.returnType}
                                                                        </TableCell>
                                                                    </TableRow>
                                                                    <TableRow key={101}>
                                                                        <TableCell component="th" scope="row">
                                                                            {"Class Name"}
                                                                        </TableCell>
                                                                        <TableCell align="left">
                                                                            {props.clonePair.a.msController.className}
                                                                        </TableCell>
                                                                    </TableRow>

                                                                    <TableRow key={102}>
                                                                        <TableCell component="th" scope="row">
                                                                            {"Method Name"}
                                                                        </TableCell>
                                                                        <TableCell align="left">
                                                                            {props.clonePair.a.msControllerMethod.methodName}
                                                                        </TableCell>
                                                                    </TableRow>
                                                                    {props.clonePair.a.msControllerMethod.msArgumentList.map((n) => (
                                                                        <TableRow key={102}>
                                                                            <TableCell component="th" scope="row">
                                                                                {"Argument"}
                                                                            </TableCell>
                                                                            <TableCell align="left">
                                                                                {n.returnType}
                                                                            </TableCell>
                                                                        </TableRow>
                                                                    ))}
                                                                    {props.clonePair.a.msControllerMethod.msAnnotations.map((n) => (
                                                                        <TableRow key={102}>
                                                                            <TableCell component="th" scope="row">
                                                                                {"Annotation"}
                                                                            </TableCell>
                                                                            <TableCell align="left">
                                                                                @{n.annotationName}({n.key}={n.value})
                                                                            </TableCell>
                                                                        </TableRow>
                                                                    ))}
                                                                </TableBody>
                                                            </Table>
                                                        </TableContainer>


                                                        {props.clonePair.a.msControllerMethod && props.clonePair.a.msControllerMethod.code &&
                                                        <Box mt={2}>
                                                            <CodeSnippet code={props.clonePair.a.msControllerMethod.code} language={'java'} showLineNumbers={false} />
                                                        </Box>

                                                        }
                                                    </Grid>

                                                    <Grid item xs={6}>
                                                        <TableContainer component={Paper} className={classes.paper}>
                                                            <Table aria-label="properties table">
                                                                <TableBody>
                                                                    <TableRow key={99}>
                                                                        <TableCell component="th" scope="row">
                                                                            <b>{"Controller"}</b>
                                                                        </TableCell>
                                                                        <TableCell align="left">

                                                                        </TableCell>
                                                                    </TableRow>
                                                                    <TableRow key={100}>
                                                                        <TableCell component="th" scope="row">
                                                                            {"Return Type"}
                                                                        </TableCell>
                                                                        <TableCell align="left">
                                                                            {props.clonePair.b.msControllerMethod.returnType}
                                                                        </TableCell>
                                                                    </TableRow>
                                                                    <TableRow key={101}>
                                                                        <TableCell component="th" scope="row">
                                                                            {"Class Name"}
                                                                        </TableCell>
                                                                        <TableCell align="left">
                                                                            {props.clonePair.b.msController.className}
                                                                        </TableCell>
                                                                    </TableRow>

                                                                    <TableRow key={102}>
                                                                        <TableCell component="th" scope="row">
                                                                            {"Method Name"}
                                                                        </TableCell>
                                                                        <TableCell align="left">
                                                                            {props.clonePair.b.msControllerMethod.methodName}
                                                                        </TableCell>
                                                                    </TableRow>
                                                                    {props.clonePair.b.msControllerMethod.msArgumentList.map((n) => (
                                                                        <TableRow key={102}>
                                                                            <TableCell component="th" scope="row">
                                                                                {"Argument"}
                                                                            </TableCell>
                                                                            <TableCell align="left">
                                                                                {n.returnType}
                                                                            </TableCell>
                                                                        </TableRow>
                                                                    ))}
                                                                    {props.clonePair.b.msControllerMethod.msAnnotations.map((n) => (
                                                                        <TableRow key={102}>
                                                                            <TableCell component="th" scope="row">
                                                                                {"Annotation"}
                                                                            </TableCell>
                                                                            <TableCell align="left">
                                                                                @{n.annotationName}({n.key}={n.value})
                                                                            </TableCell>
                                                                        </TableRow>
                                                                    ))}
                                                                </TableBody>
                                                            </Table>
                                                        </TableContainer>

                                                        {props.clonePair.b.msControllerMethod && props.clonePair.b.msControllerMethod.code &&
                                                        <Box mt={2}>
                                                            <CodeSnippet code={props.clonePair.b.msControllerMethod.code} language={'java'} showLineNumbers={false} />
                                                        </Box>
                                                        }
                                                    </Grid>
                                                </Grid>


                                                {/* SERVICE */}
                                                <Grid container spacing={2}>
                                                    <Grid item xs={6}>
                                                        {props.clonePair.a.msServiceMethod &&
                                                        <TableContainer component={Paper} className={classes.paper}>
                                                            <Table aria-label="properties table">
                                                                <TableBody>
                                                                    <TableRow key={99}>
                                                                        <TableCell component="th" scope="row">
                                                                            <b>{"Service"}</b>
                                                                        </TableCell>
                                                                        <TableCell align="left">

                                                                        </TableCell>
                                                                    </TableRow>
                                                                    <TableRow key={100}>
                                                                        <TableCell component="th" scope="row">
                                                                            {"Return Type"}
                                                                        </TableCell>
                                                                        <TableCell align="left">
                                                                            {props.clonePair.a.msServiceMethod && props.clonePair.a.msServiceMethod.returnType}
                                                                        </TableCell>
                                                                    </TableRow>
                                                                    <TableRow key={101}>
                                                                        <TableCell component="th" scope="row">
                                                                            {"Class Name"}
                                                                        </TableCell>
                                                                        <TableCell align="left">
                                                                            {props.clonePair.a.msServiceMethod && props.clonePair.a.msService.className}
                                                                        </TableCell>
                                                                    </TableRow>

                                                                    <TableRow key={102}>
                                                                        <TableCell component="th" scope="row">
                                                                            {"Method Name"}
                                                                        </TableCell>
                                                                        <TableCell align="left">
                                                                            {props.clonePair.a.msServiceMethod && props.clonePair.a.msServiceMethod.methodName}
                                                                        </TableCell>
                                                                    </TableRow>
                                                                    {props.clonePair.a.msServiceMethod && props.clonePair.a.msServiceMethod.msArgumentList.map((n) => (
                                                                        <TableRow key={102}>
                                                                            <TableCell component="th" scope="row">
                                                                                {"Argument"}
                                                                            </TableCell>
                                                                            <TableCell align="left">
                                                                                {n.returnType}
                                                                            </TableCell>
                                                                        </TableRow>
                                                                    ))}

                                                                    {props.clonePair.a.msServiceMethod && props.clonePair.a.msServiceMethod.msAnnotations.map((n) => {
                                                                        let isKey = n.key ? true : false;
                                                                        return (
                                                                            <TableRow key={102}>
                                                                                <TableCell component="th" scope="row">
                                                                                    {"Annotation"}
                                                                                </TableCell>
                                                                                {isKey && (
                                                                                    <TableCell align="left">
                                                                                        @{n.annotationName}({n.key}={n.value})
                                                                                    </TableCell>
                                                                                )}

                                                                                {!isKey && (
                                                                                    <TableCell align="left">
                                                                                        @{n.annotationName}
                                                                                    </TableCell>
                                                                                )}

                                                                            </TableRow>
                                                                        )
                                                                    })}


                                                                </TableBody>
                                                            </Table>
                                                        </TableContainer>
                                                        }
                                                        {props.clonePair.a.msServiceMethod && props.clonePair.a.msServiceMethod.code &&
                                                        <Box mt={2}>
                                                            <CodeSnippet code={props.clonePair.a.msServiceMethod.code} language={'java'} showLineNumbers={false} />
                                                        </Box>
                                                        }
                                                    </Grid>

                                                    <Grid item xs={6}>
                                                        {props.clonePair.b.msServiceMethod &&
                                                        <TableContainer component={Paper} className={classes.paper}>
                                                            <Table aria-label="properties table">
                                                                <TableBody>
                                                                    <TableRow key={99}>
                                                                        <TableCell component="th" scope="row">
                                                                            <b>{"Service"}</b>
                                                                        </TableCell>
                                                                        <TableCell align="left">

                                                                        </TableCell>
                                                                    </TableRow>
                                                                    <TableRow key={100}>
                                                                        <TableCell component="th" scope="row">
                                                                            {"Return Type"}
                                                                        </TableCell>
                                                                        <TableCell align="left">
                                                                            {props.clonePair.b.msServiceMethod && props.clonePair.b.msServiceMethod.returnType}
                                                                        </TableCell>
                                                                    </TableRow>
                                                                    <TableRow key={101}>
                                                                        <TableCell component="th" scope="row">
                                                                            {"Class Name"}
                                                                        </TableCell>
                                                                        <TableCell align="left">
                                                                            {props.clonePair.b.msServiceMethod && props.clonePair.b.msService.className}
                                                                        </TableCell>
                                                                    </TableRow>

                                                                    <TableRow key={102}>
                                                                        <TableCell component="th" scope="row">
                                                                            {"Method Name"}
                                                                        </TableCell>
                                                                        <TableCell align="left">
                                                                            {props.clonePair.b.msServiceMethod && props.clonePair.b.msServiceMethod.methodName}
                                                                        </TableCell>
                                                                    </TableRow>
                                                                    {props.clonePair.b.msServiceMethod && props.clonePair.b.msServiceMethod.msArgumentList.map((n) => (
                                                                        <TableRow key={102}>
                                                                            <TableCell component="th" scope="row">
                                                                                {"Argument"}
                                                                            </TableCell>
                                                                            <TableCell align="left">
                                                                                {n.returnType}
                                                                            </TableCell>
                                                                        </TableRow>
                                                                    ))}

                                                                    {props.clonePair.b.msServiceMethod && props.clonePair.b.msServiceMethod.msAnnotations.map((n) => {
                                                                        let isKey = n.key ? true : false;
                                                                        return (
                                                                            <TableRow key={102}>
                                                                                <TableCell component="th" scope="row">
                                                                                    {"Annotation"}
                                                                                </TableCell>
                                                                                {isKey && (
                                                                                    <TableCell align="left">
                                                                                        @{n.annotationName}({n.key}={n.value})
                                                                                    </TableCell>
                                                                                )}

                                                                                {!isKey && (
                                                                                    <TableCell align="left">
                                                                                        @{n.annotationName}
                                                                                    </TableCell>
                                                                                )}

                                                                            </TableRow>
                                                                        )
                                                                    })}


                                                                </TableBody>
                                                            </Table>
                                                        </TableContainer>
                                                        }
                                                        {props.clonePair.b.msServiceMethod && props.clonePair.b.msServiceMethod.code &&
                                                        <Box mt={2}>
                                                            <CodeSnippet code={props.clonePair.b.msServiceMethod.code} language={'java'} showLineNumbers={false} />
                                                        </Box>
                                                        }
                                                    </Grid>
                                                </Grid>

                                                {/* REPOSITORY */}
                                                <Grid container spacing={2}>
                                                    <Grid item xs={6}>
                                                        {props.clonePair.a.msRepositoryMethod &&
                                                        <TableContainer component={Paper} className={classes.paper}>
                                                            <Table aria-label="properties table">
                                                                <TableBody>
                                                                    <TableRow key={99}>
                                                                        <TableCell component="th" scope="row">
                                                                            <b>{"Repository"}</b>
                                                                        </TableCell>
                                                                        <TableCell align="left">

                                                                        </TableCell>
                                                                    </TableRow>
                                                                    <TableRow key={100}>
                                                                        <TableCell component="th" scope="row">
                                                                            {"Return Type"}
                                                                        </TableCell>
                                                                        <TableCell align="left">
                                                                            {props.clonePair.a.msRepositoryMethod && props.clonePair.a.msRepositoryMethod.returnType}
                                                                        </TableCell>
                                                                    </TableRow>
                                                                    <TableRow key={101}>
                                                                        <TableCell component="th" scope="row">
                                                                            {"Class Name"}
                                                                        </TableCell>
                                                                        <TableCell align="left">
                                                                            {props.clonePair.a.msRepositoryMethod && props.clonePair.a.msRepositoryMethod.className}
                                                                        </TableCell>
                                                                    </TableRow>

                                                                    <TableRow key={102}>
                                                                        <TableCell component="th" scope="row">
                                                                            {"Method Name"}
                                                                        </TableCell>
                                                                        <TableCell align="left">
                                                                            {props.clonePair.a.msRepositoryMethod && props.clonePair.a.msRepositoryMethod.methodName}
                                                                        </TableCell>
                                                                    </TableRow>
                                                                    {props.clonePair.a.msRepositoryMethod && props.clonePair.a.msRepositoryMethod.msArgumentList.map((n) => (
                                                                        <TableRow key={102}>
                                                                            <TableCell component="th" scope="row">
                                                                                {"Argument"}
                                                                            </TableCell>
                                                                            <TableCell align="left">
                                                                                {n.returnType}
                                                                            </TableCell>
                                                                        </TableRow>
                                                                    ))}

                                                                    {props.clonePair.a.msServiceMethod && props.clonePair.a.msServiceMethod.msAnnotations.map((n) => {
                                                                        let isKey = n.key ? true : false;
                                                                        return (
                                                                            <TableRow key={102}>
                                                                                <TableCell component="th" scope="row">
                                                                                    {"Annotation"}
                                                                                </TableCell>
                                                                                {isKey && (
                                                                                    <TableCell align="left">
                                                                                        @{n.annotationName}({n.key}={n.value})
                                                                                    </TableCell>
                                                                                )}

                                                                                {!isKey && (
                                                                                    <TableCell align="left">
                                                                                        @{n.annotationName}
                                                                                    </TableCell>
                                                                                )}

                                                                            </TableRow>
                                                                        )
                                                                    })}


                                                                </TableBody>
                                                            </Table>
                                                        </TableContainer>
                                                        }

                                                        {props.clonePair.a.msRepositoryMethod && props.clonePair.a.msRepositoryMethod.code &&
                                                        <Box mt={2}>
                                                            <CodeSnippet code={props.clonePair.a.msRepositoryMethod.code} language={'java'} showLineNumbers={false} />
                                                        </Box>
                                                        }
                                                    </Grid>

                                                    <Grid item xs={6}>
                                                        {props.clonePair.b.msRepositoryMethod &&
                                                        <TableContainer component={Paper} className={classes.paper}>
                                                            <Table aria-label="properties table">
                                                                <TableBody>
                                                                    <TableRow key={99}>
                                                                        <TableCell component="th" scope="row">
                                                                            <b>{"Repository"}</b>
                                                                        </TableCell>
                                                                        <TableCell align="left">

                                                                        </TableCell>
                                                                    </TableRow>
                                                                    <TableRow key={100}>
                                                                        <TableCell component="th" scope="row">
                                                                            {"Return Type"}
                                                                        </TableCell>
                                                                        <TableCell align="left">
                                                                            {props.clonePair.b.msRepositoryMethod && props.clonePair.b.msRepositoryMethod.returnType}
                                                                        </TableCell>
                                                                    </TableRow>
                                                                    <TableRow key={101}>
                                                                        <TableCell component="th" scope="row">
                                                                            {"Class Name"}
                                                                        </TableCell>
                                                                        <TableCell align="left">
                                                                            {props.clonePair.b.msRepositoryMethod && props.clonePair.b.msRepositoryMethod.className}
                                                                        </TableCell>
                                                                    </TableRow>

                                                                    <TableRow key={102}>
                                                                        <TableCell component="th" scope="row">
                                                                            {"Method Name"}
                                                                        </TableCell>
                                                                        <TableCell align="left">
                                                                            {props.clonePair.b.msRepositoryMethod && props.clonePair.b.msRepositoryMethod.methodName}
                                                                        </TableCell>
                                                                    </TableRow>
                                                                    {props.clonePair.b.msRepositoryMethod && props.clonePair.b.msRepositoryMethod.msArgumentList.map((n) => (
                                                                        <TableRow key={102}>
                                                                            <TableCell component="th" scope="row">
                                                                                {"Argument"}
                                                                            </TableCell>
                                                                            <TableCell align="left">
                                                                                {n.returnType}
                                                                            </TableCell>
                                                                        </TableRow>
                                                                    ))}

                                                                    {props.clonePair.b.msServiceMethod && props.clonePair.b.msServiceMethod.msAnnotations.map((n) => {
                                                                        let isKey = n.key ? true : false;
                                                                        return (
                                                                            <TableRow key={102}>
                                                                                <TableCell component="th" scope="row">
                                                                                    {"Annotation"}
                                                                                </TableCell>
                                                                                {isKey && (
                                                                                    <TableCell align="left">
                                                                                        @{n.annotationName}({n.key}={n.value})
                                                                                    </TableCell>
                                                                                )}

                                                                                {!isKey && (
                                                                                    <TableCell align="left">
                                                                                        @{n.annotationName}
                                                                                    </TableCell>
                                                                                )}

                                                                            </TableRow>
                                                                        )
                                                                    })}


                                                                </TableBody>
                                                            </Table>
                                                        </TableContainer>
                                                        }

                                                        {props.clonePair.b.msRepositoryMethod && props.clonePair.b.msRepositoryMethod.code &&
                                                        <Box mt={2}>
                                                            <CodeSnippet code={props.clonePair.b.msRepositoryMethod.code} language={'java'} showLineNumbers={false} />
                                                        </Box>
                                                        }
                                                    </Grid>
                                                </Grid>
                                            </div>
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
export default ClonePairMain;