import {useGlobalState} from "../../../state/appState";
import CodeCloneList from "../clones/CodeCloneList";
import React from "react";
import InconsistencyList from "../inconsistencies/InconsistencyList";
import {AppBar, Box, Card, CardContent, Paper, Tab, Tabs, TextField, Typography} from "@material-ui/core";
import {createStyles, makeStyles, Theme} from "@material-ui/core/styles";

interface TabPanelProps {
    children?: React.ReactNode;
    index: any;
    value: any;
}

function TabPanel(props: TabPanelProps) {
    const { children, value, index, ...other } = props;

    return (
        <div
            role="tabpanel"
            hidden={value !== index}
            id={`simple-tabpanel-${index}`}
            aria-labelledby={`simple-tab-${index}`}
            {...other}
        >
            {value === index && (
                <Box p={3}>
                    <Typography>{children}</Typography>
                </Box>
            )}
        </div>
    );
}

function a11yProps(index: any) {
    return {
        id: `simple-tab-${index}`,
        'aria-controls': `simple-tabpanel-${index}`,
    };
}

const useStyles = makeStyles((theme: Theme) =>
    createStyles({
        root: {
            flexGrow: 1,
        },
        paper: {
            padding: theme.spacing(2),
            // color: theme.palette.text.secondary,
        },
        content: {
            flexGrow: 1,
            padding: theme.spacing(2, 0, 2),
            backgroundColor: theme.palette.background.default
        }
    }),
);

const AppData = () => {
    const classes = useStyles();
    const [analyzedData, setAnalyzedData] = useGlobalState('analyzedData');
    const [value, setValue] = React.useState(0);

    const handleChange = (event: React.ChangeEvent<{}>, newValue: number) => {
        setValue(newValue);
    };
    return (
        <div>
            {(analyzedData.inconsistencies || analyzedData.typeA || analyzedData.typeB) && (
                <div>
                    <Paper className={classes.paper}>
                        <Card style={{marginBottom: '20px'}} >
                            <CardContent>
                                <Box m={1}>
                                    <div className={classes.content}>
                                        <AppBar position="static">
                                            <Tabs value={value} onChange={handleChange} aria-label="simple tabs example">
                                                <Tab label="Inconsistencies" {...a11yProps(0)} />
                                                <Tab label="Code Clones" {...a11yProps(1)} />
                                            </Tabs>
                                        </AppBar>
                                        <TabPanel value={value} index={0}>
                                            <InconsistencyList inconsistencyList={analyzedData.inconsistencies} />
                                        </TabPanel>
                                        <TabPanel value={value} index={1}>
                                            <CodeCloneList cloneList={analyzedData.typeA} />
                                            <CodeCloneList cloneList={analyzedData.typeB} />
                                        </TabPanel>
                                    </div>
                                </Box>
                            </CardContent>
                        </Card>
                    </Paper>
                </div>
            )}
        </div>
    )
}
export default AppData;