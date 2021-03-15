import React from "react";
import {Box, Card, Typography} from "@material-ui/core";
import Paper from "@material-ui/core/Paper";
import Table from "@material-ui/core/Table";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import TableCell from "@material-ui/core/TableCell";
import TableBody from "@material-ui/core/TableBody";
import TableContainer from "@material-ui/core/TableContainer";

type Props = {
    similarityController: any;
    similarityService: any;
    similarityRepository: any;
    similarityRestCalls: any;
    globalSimilarity: any;
    typeA: any;
    typeB: any;
}

const CloneSummary = (props: Props) => {
    return (
        <div>

            <Typography variant="subtitle1" gutterBottom>

            </Typography>
            {props.typeA}
            {props.typeB}

            <TableContainer component={Paper}>
                <Table aria-label="simple table">
                    <TableHead>
                        <TableRow>
                            <TableCell>
                                <Box fontWeight="fontWeightBold" m={0}>Similarity</Box>
                            </TableCell>
                            <TableCell align="left">
                                <Box fontWeight="fontWeightBold" m={0}>Percentage</Box>
                            </TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        <TableRow key={1}>
                            <TableCell component="th" scope="row">
                                {"Controller"}
                            </TableCell>
                            <TableCell align="left">{props.similarityController * 100} %</TableCell>
                        </TableRow>
                        <TableRow key={2}>
                            <TableCell component="th" scope="row">
                                {"Service"}
                            </TableCell>
                            <TableCell align="left">{props.similarityService * 100} %</TableCell>
                        </TableRow>
                        <TableRow key={3}>
                            <TableCell component="th" scope="row">
                                {"Rest Calls"}
                            </TableCell>
                            <TableCell align="left">{props.similarityRestCalls * 100} %</TableCell>
                        </TableRow>
                        <TableRow key={4}>
                            <TableCell component="th" scope="row">
                                {"Rest Calls"}
                            </TableCell>
                            <TableCell align="left">{props.similarityRestCalls * 100} %</TableCell>
                        </TableRow>
                        <TableRow key={5}>
                            <TableCell component="th" scope="row">
                                <Box fontWeight="fontWeightBold" m={0}>
                                    {"Global Similarity"}
                                </Box>
                            </TableCell>
                            <TableCell align="left">
                                <Box fontWeight="fontWeightBold" m={0}>
                                    {props.globalSimilarity * 100} %
                                </Box>
                            </TableCell>
                        </TableRow>
                    </TableBody>
                </Table>
            </TableContainer>


        </div>
    );
}
export default CloneSummary;