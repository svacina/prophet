import * as React from 'react';
// import './App.css';
import {HashRouter as Router, Route, Switch} from "react-router-dom"
import {withRoot} from "./withRoot";
import { createBrowserHistory } from 'history';
// theme
import { ThemeProvider } from '@material-ui/core/styles';
import prophetTheme from "./prophetTheme";
import prophetStyles from "./prophetStyles";
// material ui
import CssBaseline from '@material-ui/core/CssBaseline';
// components
import AppHeader from "./components/main/AppHeader";
import ProphetApp from './cc/components/main/ProphetApp';

const App = () => {
    const classes = prophetStyles();
    const history = createBrowserHistory();

    return (
        <div className={classes.root}>
            <CssBaseline />
            <AppHeader/>
            <ThemeProvider theme={prophetTheme}>
                <Router history={history} basename={"/"} >
                    <Switch>
                        <Route exact path="/" component={ProphetApp} />
                    </Switch>
                </Router>
            </ThemeProvider>
        </div>
    );
}

export default withRoot(App);
