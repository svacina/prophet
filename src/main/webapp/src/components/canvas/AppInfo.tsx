import React from "react";
import {useGlobalState} from "../../state/appState";
import SingleConfig from "../configuration/SingleConfig";
import {Repository} from "../../model/Repository";
import Typography from '@material-ui/core/Typography';
import {Card} from '@material-ui/core';
import { CardContent } from '@material-ui/core';

/**
 *
 * @constructor
 *
 */
const AppInfo = () => {

    return (
        <React.Fragment>
            <Card style={{marginBottom: '20px'}}>
                <CardContent >
                    <Typography component="h4" variant="h4">
                        Welcome to Prophet!
                    </Typography>
                    <p>
                        Prophet is a microservice analysis platform that automatically extracts class diagrams and communication diagrams from a microservice system. The different components of Prophet are available here:
                        <ul>
                            <li><a href="https://github.com/cloudhubs/prophet-web">Frontend</a></li>
                            <li><a href="https://github.com/cloudhubs/prophet-utils-app">Backend server</a></li>
                            <li><a href="https://github.com/cloudhubs/prophet-utils">Backend utils</a></li>
                            <li><a href="https://github.com/cloudhubs/prophet-dto">Backend classes</a></li>
                        </ul>
                    </p>
                </CardContent>
            </Card>
        </React.Fragment>
    )
}
export default AppInfo;
