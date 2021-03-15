import React from "react";
import {Box, Card, CardContent, Paper, TextField} from "@material-ui/core";
import Button from "@material-ui/core/Button";
import { useGlobalState } from "../../../state/appState";
import {createStyles, makeStyles, Theme} from "@material-ui/core/styles";
import OpenAppData from "../../../http/OpenAppData";
import AnalyzeAppData from "../../../http/AnalyzeAppData";

const useStyles = makeStyles((theme: Theme) =>
    createStyles({
        root: {
            flexGrow: 1,
        },
        paper: {
            padding: theme.spacing(2),
            // color: theme.palette.text.secondary,
        },
    }),
);


const AppSetup = () => {
    const classes = useStyles();
    const [sutPath, sutPathUrl] = useGlobalState('sutPath');
    const [sutError] = useGlobalState('sutError');

    const [idePath, setIdePath] = useGlobalState('idePath');
    const [ideError] = useGlobalState('ideError');

    const [ideExe, setIdeExe] = useGlobalState('ideExe');
    const [ideExeError] = useGlobalState('ideExeError');

    const [analyzedData, setAnalyzedData] = useGlobalState('analyzedData');

    const handleSutChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        e.preventDefault();
        sutPathUrl(e.target.value);
    }

    const handleIdeChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        e.preventDefault();
        setIdePath(e.target.value);
    }

    const handleExeChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        e.preventDefault();
        setIdeExe(e.target.value);
    }


    const handleSubmit = async (e) => {
        e.preventDefault();
        await OpenAppData.open(sutPath, idePath, ideExe);
        const body = await AnalyzeAppData.analyze(sutPath, idePath, ideExe);
        console.log(body);
        setAnalyzedData(body);
    }

    return (
        <Paper className={classes.paper}>
            <Card style={{marginBottom: '20px'}} >
                <CardContent >
                    <Box m={1}>
                        <form noValidate autoComplete="off" onSubmit={handleSubmit}>

                            <TextField
                                id="idePath-url"
                                error={ideError}
                                label="Path to IDE"
                                type="text"
                                style={{ margin: 8 }}
                                placeholder="Path to IDE"
                                fullWidth
                                margin="normal"
                                InputLabelProps={{
                                    shrink: true,
                                }}
                                value={idePath}
                                onChange={handleIdeChange}
                                helperText={ideError && <p>Repository does not exist!</p>}
                            />

                            <TextField
                                id="ide-url"
                                error={sutError}
                                label="Executable script"
                                type="text"
                                style={{ margin: 8 }}
                                placeholder="./code"
                                fullWidth
                                margin="normal"
                                InputLabelProps={{
                                    shrink: true,
                                }}
                                value={ideExe}
                                onChange={handleExeChange}
                                helperText={ideExeError && <p>Path does not exist!</p>}
                            />

                            <TextField
                                id="sutPath-url"
                                error={sutError}
                                label="Path to System Under Test"
                                type="text"
                                style={{ margin: 8 }}
                                placeholder="/home/user/dev/sut/path"
                                fullWidth
                                margin="normal"
                                InputLabelProps={{
                                    shrink: true,
                                }}
                                value={sutPath}
                                onChange={handleSutChange}
                                helperText={sutError && <p>Path does not exist!</p>}
                            />



                            <Button variant="contained" color="primary" type="submit">
                                Analyze
                            </Button>
                        </form>
                    </Box>
                </CardContent>
            </Card>
        </Paper>
    )
}
export default AppSetup;
