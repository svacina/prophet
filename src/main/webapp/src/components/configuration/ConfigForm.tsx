import React from "react";
import {Box, TextField} from "@material-ui/core";
import {Repository} from "../../model/Repository";
import Button from "@material-ui/core/Button";
import DoAnalyze from "../../http/DoAnalyze";
import {useGlobalState} from "../../state/appState";

type ConfigProps = {
    conf: Repository
}

/**
 * @author Jan Svacina
 * @created May 8th 2020
 * @lastRevision May 24th 2020
 * @description Gets Github URL from input
 */
const ConfigForm = (conf: ConfigProps) => {

    const [url, setUrl] = useGlobalState('githubUrl');
    const [gitError] = useGlobalState('gitError');

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setUrl(e.target.value);
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        await DoAnalyze.get(url);
    }

    return (
        <Box m={1}>
            <form noValidate autoComplete="off" onSubmit={handleSubmit}>
                <TextField
                    id="github-url"
                    error={gitError}
                    label="Repository URL (GitHub /organization/repository)"
                    type="text"
                    style={{ margin: 8 }}
                    placeholder="GitHub URL here"
                    fullWidth
                    margin="normal"
                    InputLabelProps={{
                        shrink: true,
                    }}
                    value={url}
                    onChange={handleChange}
                    helperText={gitError && <p>Repository does not exist!</p>}
                />

                <Button variant="contained" color="primary" type="submit">
                    Analyze
                </Button>
            </form>
        </Box>
    )
}
export default ConfigForm;
