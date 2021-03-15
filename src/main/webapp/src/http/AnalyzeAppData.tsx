import ActionsRegister from "../state/ActionsRegister";

const AnalyzeAppData = {

    async analyze(sutPath: string, idePath: string, ideExe: string) {
        this.startLoading();
        const response = await this.getData(sutPath);
        if (response != null) {
            this.stopLoading();
            return await this.handleBody(response);
        }
        this.stopLoading();
    },
    async getData(sutPath) {
        return await fetch( 'http://localhost:8080/analyze', {
            method: 'POST',
            body: JSON.stringify({
                sutRoot: sutPath,
                dataOutputPath: "/tmp",
                logResults: false,
                logStats: false
            }),
            headers : {
                'Content-Type': 'application/json',
                Accept: 'application/json',
            }
        });
    },
    async handleBody(response){
        const body = await response.json();
        // ActionsRegister.setProphetResponse(body.global, body.ms);
        // setTimeout(() => {}, 3000);
        // ActionsRegister.stopLoading();
        return body;
    },
    stopLoading(){
        console.log("server error");
        ActionsRegister.stopLoading();
        return [];
    },
    showErrors(){
        ActionsRegister.stopLoading();
        ActionsRegister.showGitError();
    },
    startLoading(){
        ActionsRegister.startLoading();
        ActionsRegister.hideGitError();
    },
}

export default AnalyzeAppData;