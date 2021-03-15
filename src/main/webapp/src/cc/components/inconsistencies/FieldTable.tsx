import React from "react";
import {Typography} from "@material-ui/core";
import Paper from "@material-ui/core/Paper";
import Table from "@material-ui/core/Table";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import TableCell from "@material-ui/core/TableCell";
import Box from "@material-ui/core/Box";
import TableBody from "@material-ui/core/TableBody";
import TableContainer from "@material-ui/core/TableContainer";

const FieldTable = (missingFields) => {

    return (
        <React.Fragment>
            <Typography variant="h6" gutterBottom>
                Missing Fields
            </Typography>
            <TableContainer component={Paper}>
                <Table aria-label={"missing entity fields"}>
                    <TableHead>
                        <TableRow>
                            <TableCell><Box fontWeight="fontWeightBold">Annotations</Box></TableCell>
                            <TableCell><Box fontWeight="fontWeightBold">Type</Box></TableCell>
                            <TableCell align="left"><Box fontWeight="fontWeightBold">Name</Box></TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {missingFields.missingFields && missingFields.missingFields.map((field) => (
                            <React.Fragment>
                                <TableRow key={field.name}>
                                    <TableCell component="th" scope="row">
                                        {field.annotationsHashSet && field.annotationsHashSet.map((annotation) => (
                                            <p>{annotation} </p>
                                        ))}
                                    </TableCell>
                                    <TableCell scope="row">
                                        {field.type}
                                    </TableCell>
                                    <TableCell align="left">{field.name}</TableCell>
                                </TableRow>

                            </React.Fragment>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </React.Fragment>
    )
}
export default FieldTable;