package edu.baylor.ecs.cloudhubs.semantics.entity;

import lombok.Data;

import java.util.List;

@Data
public class MsClass {
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
