package edu.baylor.ecs.cloudhubs.semantics.entity;

import lombok.Data;

@Data
public class MsMethodCall {
    private String id;
    private String packageName;
    private String className;
    private String methodName;
    private int lineNumber;
    private String calledMethodName;
    private String calledMethodId;
    private String calledServiceId;
    private String statementDeclaration;

    public MsMethodCall() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public void setCalledMethodName(String calledMethodName) {
        this.calledMethodName = calledMethodName;
    }

    public void setCalledMethodId(String calledMethodId) {
        this.calledMethodId = calledMethodId;
    }

    public void setCalledServiceId(String calledServiceId) {
        this.calledServiceId = calledServiceId;
    }

    public void setMsParentMethod(MsParentMethod msParentMethod) {
        this.packageName = msParentMethod.getPackageName();
        this.className = msParentMethod.getClassName();
        this.methodName = msParentMethod.getMethodName();
    }

    @Override
    public String toString() {
        return "[L" + lineNumber + "] " +
                packageName + '.' +
                className + '.' +
                methodName +
                " -> " +
                calledServiceId + '.' +
                calledMethodName + '.'
        ;
    }
}
