const OpenAppData = {
    async open(sutPath: string, idePath: string, ideExe: string) {
        const response = await this.queryServer(sutPath, idePath, ideExe);
        if (response != null) {
            return await this.handleBody(response);
        }
    },
    async queryServer(sutPath: string, idePath: string, ideExe: string) {
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({"ide": ideExe, "idePath":idePath, "filePath": sutPath})
        };
        return await fetch('http://localhost:8080/open', requestOptions)
            .then(response => response.json())
            .then(data => console.log(data));
    },
    async handleBody(response){
        const body = await response.json();
        // ActionsRegister.setProphetResponse(body.global, body.ms);
        // setTimeout(() => {}, 3000);
        // ActionsRegister.stopLoading();
        return body;
    },
}

export default OpenAppData;