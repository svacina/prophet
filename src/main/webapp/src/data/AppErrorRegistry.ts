import {ServerErrors} from "../model/ServerErrors";
import {ErrorItem} from "../model/ErrorItem";


const appErrors = [
    new ErrorItem(0, "No supported annotations found"),
    new ErrorItem( 1, "Java code not found"),
new ErrorItem(10, "Provided address is not Github URL"),
new ErrorItem (11, "Provided address does not contain valid organization"),
new ErrorItem (12, "Provided address does not contain valid repository"),
]

const AppErrorRegistry = {
    getAnalysisErrors() {
        let analysisErrors = new ServerErrors();
        let annotationError = new ErrorItem(0, "No supported annotations found");
        let javaError = new ErrorItem( 1, "Java code not found");
        let notGitHubUrl = new ErrorItem(10, "Provided address is not Github URL");
        let notOrganization = new ErrorItem (11, "Provided address does not contain valid organization");
        let notRepository = new ErrorItem (12, "Provided address does not contain valid repository");
        analysisErrors.errors.push(annotationError);
        analysisErrors.errors.push(javaError);
        analysisErrors.errors.push(notGitHubUrl);
        analysisErrors.errors.push(notOrganization);
        analysisErrors.errors.push(notRepository);
        return analysisErrors;
    }
}

export default AppErrorRegistry;
