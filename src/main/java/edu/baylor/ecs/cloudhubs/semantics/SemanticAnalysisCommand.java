package edu.baylor.ecs.cloudhubs.semantics;

import javax.enterprise.context.control.ActivateRequestContext;
import javax.inject.Inject;

import edu.baylor.ecs.cloudhubs.semantics.util.ProcessFiles;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class SemanticAnalysisCommand implements QuarkusApplication {

    @Override
    public int run(String... args) throws Exception {
        //print
        final String name = args.length > 0 ? String.join(" ", args) : "";
        // System.out.println(name);
        ProcessFiles.run(args);
        return 0;
    }
}
