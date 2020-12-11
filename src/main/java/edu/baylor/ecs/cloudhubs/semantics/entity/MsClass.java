package edu.baylor.ecs.cloudhubs.semantics.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MsClass implements Serializable {
    private String path;
    private String classId;
    private String packageName;
    private String className;
    private List<String> fieldNames;
    private MsClassRoles role;

    public void setIds(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.getPackageName());
        sb.append(".");
        sb.append(this.getClassName());
        this.classId = sb.toString();
    }

    @Override
    public String toString() {
        return this.getClassId() + " [" + this.getRole() + "]";
    }

}
