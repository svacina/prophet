import ActionsRegister from "../state/ActionsRegister";
import * as React from "react";
import FetchMetadata from "./FetchMetadata";

const DoAnalyze = {
    /**
     * Main analysis function
     * @param path: Path to Github repository, e.g. cloudhubs/tms
     */
    async get(path: string) {
        this.startLoading();
        const git = await this.getGit(path);
        if (git == null) {
            this.showErrors();
            return;
        }
        const response = await this.getProphetData(path);
        if (response != null){
            return await this.handleBody(response);
        } else {
            return this.stopLoading();
        }
    },
    async getProphetData(path: string) {
        return await fetch( 'http://127.0.0.1:8081/', {
        // return await fetch( 'https://cloudhubs.ecs.baylor.edu/prophet/utils', {
            method: 'POST',
            body: JSON.stringify({
                repositories: [
                    {
                        path: path,
                        isMonolith: true
                    }
                ],
                systemName: "tms"
            }),
            headers : {
                'Content-Type': 'application/json',
                Accept: 'application/json',
            }
        });
    },
    /**
     *
     * @param response: Response from prophet-app
     */
    async handleBody(response){
        const body = await response.json();
        ActionsRegister.setProphetResponse(body.global, body.ms);
        setTimeout(() => {}, 3000);
        ActionsRegister.stopLoading();
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
    async getGit(path: string){
        let split: string[] = path.split("/");
        return await FetchMetadata.getRepositoryDetail(split[0], split[1]);
    }
}

export default DoAnalyze;
