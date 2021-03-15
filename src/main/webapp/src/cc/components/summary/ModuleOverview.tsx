import React from "react";
import PrettyPrintJson from "../../utils/PrettyPrintJson";
const ModuleOverview = (module) => {
    return (
        <div>
            Hello from ModuleOverview
            <PrettyPrintJson data={module} />
        </div>
    );
}
export default ModuleOverview;