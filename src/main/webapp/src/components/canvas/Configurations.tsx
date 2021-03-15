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
const Configurations = () => {

    const [request] = useGlobalState('request');

    return (
        <React.Fragment>
            <Card style={{marginBottom: '20px'}}>
                <CardContent >
                    <Typography component="h4" variant="h4">
                        Repository
                    </Typography>
                    {request.repositories.map((m: Repository) => (
                        <>
                            <SingleConfig conf={m}/>
                        </>
                    ))}
                </CardContent>
            </Card>
        </React.Fragment>
    )
}
export default Configurations;
