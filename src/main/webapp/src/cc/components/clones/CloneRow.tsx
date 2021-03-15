import TableCell from "@material-ui/core/TableCell";
import TableRow from "@material-ui/core/TableRow";


import React from "react";
const CloneRow = ({row, key, value}) => {
    return (
        <TableRow key={row}>
            <TableCell component="th" scope="row">
                {key}
            </TableCell>
            <TableCell align="left">
                {value}
            </TableCell>
        </TableRow>
    );
}
export default CloneRow;

