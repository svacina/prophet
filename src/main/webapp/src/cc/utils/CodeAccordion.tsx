import React from "react";
import ExpandMoreIcon from "@material-ui/icons/ExpandMore";
import {Accordion, AccordionDetails, AccordionSummary, Typography} from "@material-ui/core";
import CodeSnippet from "./CodeSnippet";

const CodeAccordion = (code) => {

    const [value, setValue] = React.useState(true);

    return (
        <React.Fragment>
            <>
                <Accordion expanded={value}>
                    <AccordionSummary
                        expandIcon={<ExpandMoreIcon />}
                        aria-controls="panel1a-content"
                        id="panel1a-header"
                        onClick={ (e) => {
                            e.preventDefault();
                            setValue((state) => {
                                return !state;
                            });
                        }}
                    >
                        <Typography>Entity Code</Typography>
                    </AccordionSummary>
                    <AccordionDetails>
                        <CodeSnippet code={code.code} language={'java'} showLineNumbers={false} />
                    </AccordionDetails>
                </Accordion>
            </>
        </React.Fragment>
    )
}
export default CodeAccordion;