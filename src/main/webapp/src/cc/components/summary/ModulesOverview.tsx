import React from "react";
import ModuleOverview from "./ModuleOverview";
import PrettyPrintJson from "../../utils/PrettyPrintJson";
import CodeSnippet from "../../utils/CodeSnippet";

const ModulesOverview = ({modules}) => {
    return (
        <div>

            {modules && modules.map((n) => (
                    <div>
                        <ModuleOverview module={n}/>
                    </div>
                )
            )}
        </div>
    );
}
export default ModulesOverview;