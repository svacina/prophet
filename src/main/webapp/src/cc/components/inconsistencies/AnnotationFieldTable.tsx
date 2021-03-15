import React from "react";
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import Box from '@material-ui/core/Box';
import {Typography} from "@material-ui/core";

const AnnotationFieldTable = (entity) => {

    return (
        <React.Fragment>
            <div>
                <Typography variant="h6" gutterBottom>
                    Missing Field Annotations
                </Typography>
                <TableContainer component={Paper}>
                    <Table aria-label={entity.entity.code}>
                        <TableHead>
                            <TableRow>
                                <TableCell><Box fontWeight="fontWeightBold">Field</Box></TableCell>
                                <TableCell align="left"><Box fontWeight="fontWeightBold">Annotation</Box></TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {entity.entity.fields && entity.entity.fields.map((field) => (
                                <React.Fragment>
                                    {field && field.missingAnnotations.map((annotation) => (
                                        <TableRow key={annotation}>
                                            <TableCell component="th" scope="row">
                                                {annotation}
                                            </TableCell>
                                            <TableCell align="left">{field.code}</TableCell>
                                        </TableRow>
                                    ))}
                                </React.Fragment>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            </div>
        </React.Fragment>
    )
}
export default AnnotationFieldTable;