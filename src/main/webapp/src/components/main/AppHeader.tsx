import React from "react";
import CssBaseline from "@material-ui/core/CssBaseline";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import Typography from "@material-ui/core/Typography";
import prophetStyles from "../../prophetStyles";
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link
} from "react-router-dom";
import {Button} from "@material-ui/core";
/**
 *
 * @constructor
 *
 */
const AppHeader = () => {

    const classes = prophetStyles();
    // const history = useHistory();

    // const onTrainTicket = async (event: React.MouseEvent<HTMLElement>) => {
    //     event.preventDefault();
    //     history.push("/train-ticket");
    // }
    //
    // const onTms2020 = async (event: React.MouseEvent<HTMLElement>) => {
    //     event.preventDefault();
    //     history.push("/tms2020");
    // }


    return (
        <React.Fragment>
            <CssBaseline />
            <AppBar className={classes.appBar}>
                <Toolbar>
                    <Typography variant="h6" noWrap>
                        Prophet
                    </Typography>
                    {/*<Button color="inherit" onClick={(event: React.MouseEvent<HTMLElement>) => {onTrainTicket(event)}}>Train Ticket</Button>*/}
                    {/*<Button color="inherit" onClick={(event: React.MouseEvent<HTMLElement>) => {onTms2020(event)}}>TMS 2020</Button>*/}
                    {/*<Router>*/}
                    {/*    <Button color="inherit">*/}
                    {/*        <Link to="/tms2020">About</Link>*/}

                    {/*    </Button>*/}
                    {/*    <Link to="/train-ticket">Users</Link>*/}
                    {/*</Router>*/}
                </Toolbar>
            </AppBar>
        </React.Fragment>
    )
}
export default AppHeader;
