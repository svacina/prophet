package edu.baylor.ecs.cloudhubs.semantics.entity;

public class MsMethodCall {
    private String id;
    private String packageName;
    private String className;
    private String methodName;
    private int lineNumber;
    private String calledMethodName;
    private String calledMethodId;
    private String calledServiceId;

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
}
