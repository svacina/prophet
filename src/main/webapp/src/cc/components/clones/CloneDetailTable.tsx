import React from "react";
import Paper from "@material-ui/core/Paper";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableRow from "@material-ui/core/TableRow";
import TableCell from "@material-ui/core/TableCell";
import TableContainer from "@material-ui/core/TableContainer";

const CloneDetailTable = (msClass, msMethod, tableName) => {

    return (
        <React.Fragment>
            <div>
                <TableContainer component={Paper}>
                    <Table aria-label="properties table">
                        <TableBody>
                            <TableRow key={99}>
                                <TableCell component="th" scope="row">
                                    <b>{tableName}</b>
                                </TableCell>
                                <TableCell align="left">

                                </TableCell>
                            </TableRow>
                            <TableRow key={100}>
                                <TableCell component="th" scope="row">
                                    {"Return Type"}
                                </TableCell>
                                <TableCell align="left">
                                    {msMethod.returnType}
                                </TableCell>
                            </TableRow>
                            <TableRow key={101}>
                                <TableCell component="th" scope="row">
                                    {"Class Name"}
                                </TableCell>
                                <TableCell align="left">
                                    {msClass.className}
                                </TableCell>
                            </TableRow>

                            {/*<TableRow key={102}>*/}
                            {/*    <TableCell component="th" scope="row">*/}
                            {/*        {"Method Name"}*/}
                            {/*    </TableCell>*/}
                            {/*    <TableCell align="left">*/}
                            {/*        {msClass.msControllerMethod.methodName}*/}
                            {/*    </TableCell>*/}
                            {/*</TableRow>*/}
                            {/*{clone.msControllerMethod.msArgumentList.map((n) => (*/}
                            {/*    <TableRow key={102}>*/}
                            {/*        <TableCell component="th" scope="row">*/}
                            {/*            {"Argument"}*/}
                            {/*        </TableCell>*/}
                            {/*        <TableCell align="left">*/}
                            {/*            {n.returnType}*/}
                            {/*        </TableCell>*/}
                            {/*    </TableRow>*/}
                            {/*))}*/}
                            {/*{clone.msControllerMethod.msAnnotations.map((n) => (*/}
                            {/*    <TableRow key={102}>*/}
                            {/*        <TableCell component="th" scope="row">*/}
                            {/*            {"Annotation"}*/}
                            {/*        </TableCell>*/}
                            {/*        <TableCell align="left">*/}
                            {/*            @{n.annotationName}({n.key}={n.value})*/}
                            {/*        </TableCell>*/}
                            {/*    </TableRow>*/}
                            {/*))}*/}
                        </TableBody>
                    </Table>
                </TableContainer>
            </div>
        </React.Fragment>
    )
}
export default CloneDetailTable;