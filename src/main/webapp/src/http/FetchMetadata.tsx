
const GIT_API = 'https://api.github.com/';

const FetchMetadata = {

    async getAllReposFromOrganization(organization: string) {
        const response = await fetch( GIT_API + 'users/' + organization +  '/repos', {
            method: 'GET',
            headers : {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
            }
        });
        if (response != null){
            const body = await response.json();
            return body;
        } else {
            console.log("server error");
            return [];
        }
    },

    async getOrganizationDetail(organization: string) {
        const response = await fetch( GIT_API + 'users/' + organization, {
            method: 'GET',
            headers : {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
            }
        });
        if (response != null){
            const body = await response.json();
            //ToDo: trigger action
        } else {
            console.log("server error");
        }
    },

    async getRepositoryDetail(organization: string, repository: string) {
        const response = await fetch(GIT_API + 'repos/' + organization +  '/' + repository, {
            method: 'GET',
            headers : {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
            }
        });
        if (response != null){
            if (!response.ok) {
                return null;
            }
            return await response.json();
        } else {
            return null;
        }
    }

}

export default FetchMetadata;
