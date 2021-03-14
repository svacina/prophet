package edu.baylor.ecs.cloudhubs.semantics.util.file;

public class StatService {

    public static int classes = 0;
    public static int controllers = 0;
    public static int services = 0;
    public static int repositories = 0;
    public static int entities = 0;
    public static int methods = 0;
    public static int methodCalls = 0;
    public static int fields = 0;

    public static String printToString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append(classes);
        sb.append("\n");
        sb.append(controllers);
        sb.append("\n");
        sb.append(services);
        sb.append("\n");
        sb.append(repositories);
        sb.append("\n");
        sb.append(entities);
        sb.append("\n");
        sb.append(methods);
        sb.append("\n");
        sb.append(methodCalls);
        sb.append("\n");
        sb.append(fields);
        sb.append("\n");
        return sb.toString();
    }

}
