package edu.baylor.ecs.cloudhubs.examiner;

import edu.baylor.ecs.cloudhubs.examiner.services.MainDriver;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class ExaminerCommand implements QuarkusApplication {
    @Override
    public int run(String... args) throws Exception {
        long start = System.currentTimeMillis();
        MainDriver mainDriver = new MainDriver();
        mainDriver.process(args);
        System.out.println(System.currentTimeMillis() - start);
        return 0;
    }
}