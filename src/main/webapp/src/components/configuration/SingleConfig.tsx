import React from "react";
import ConfigForm from "./ConfigForm";
import {Repository} from "../../model/Repository";
import {Link} from "@material-ui/core";
import {useGlobalState} from "../../state/appState";

type ConfigProps = {
    conf: Repository
}
/**
 * Represents Configurations of a specific github repository
 * @param RepoConfig Repository configuration
 * @constructor
 * @action Update Configuration to parent component from ConfigForm
 * @action Update Organization into OrganizationDetail
 * @action Update Repository into RepositoryDetail
 * @action Update Errors into ServerErrors
 * @component: ConfigForm, ServerErrors, OrganizationDetail, RepositoryDetail
 */
const SingleConfig = ({conf}: ConfigProps) => {

    const [_, setUrl] = useGlobalState('githubUrl');

    const setTms = (e) => {
        e.preventDefault();
        setUrl("cloudhubs/tms")
    }

    const setTicket = (e) => {
        e.preventDefault();
        setUrl("FudanSELab/train-ticket")
    }

    const githubUrlInput = (
        <>
            <ConfigForm conf={conf}/>
        </>
    )

    const footer = (
        <>
            <span>Example: </span>

            <Link href="#" onClick={setTms}>
                cloudhubs/tms
            </Link>

            <span>, </span>
            <Link href="#" onClick={setTicket}>
                FudanSELab/train-ticket
            </Link>
        </>
    );
    return (
        <React.Fragment>
            {githubUrlInput}
            {footer}
        </React.Fragment>
    )
}
export default SingleConfig;
