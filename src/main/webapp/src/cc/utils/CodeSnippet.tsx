import React from "react";
import { CodeBlock, dracula } from "react-code-blocks";
import { Theme, createStyles, makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles((theme: Theme) =>
    createStyles({
        root: {
            width: '100%',
        },
    }),
);

const CodeSnippet = ({ code, language, showLineNumbers }) => {
    const classes = useStyles();
    return (
        <div className={classes.root}>
            <CodeBlock
                text={code}
                language={language}
                showLineNumbers={showLineNumbers}
                theme={dracula}
            />
        </div>
    );
}
export default CodeSnippet;

