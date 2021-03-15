import React from "react";

const PrettyPrintJson = (data) => {

    return (
        <React.Fragment>
            <div><pre>{JSON.stringify(data, null, 2) }</pre></div>
        </React.Fragment>
    )
}
export default PrettyPrintJson;