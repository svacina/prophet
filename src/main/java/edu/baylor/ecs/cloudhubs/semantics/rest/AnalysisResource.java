package edu.baylor.ecs.cloudhubs.semantics.rest;

import edu.baylor.ecs.cloudhubs.semantics.dto.AnalyzeSutArgs;
import edu.baylor.ecs.cloudhubs.semantics.dto.AnalyzeTime;
import edu.baylor.ecs.cloudhubs.semantics.dto.AnalyzedCache;
import edu.baylor.ecs.cloudhubs.semantics.dto.OpenIdeArgs;
import edu.baylor.ecs.cloudhubs.semantics.service.AnalysisService;
import edu.baylor.ecs.cloudhubs.semantics.service.StressTestService;
import edu.baylor.ecs.cloudhubs.semantics.service.CommandService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class AnalysisResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/open")
    public OpenIdeArgs open(OpenIdeArgs openIdeArgs) {
        System.out.println(openIdeArgs.getIde() + " " + openIdeArgs.getFilePath());
        CommandService commandService = new CommandService();
        commandService.executeCommands(openIdeArgs);
        return openIdeArgs;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/analyze")
    public AnalyzedCache analyze(AnalyzeSutArgs analyzeSutArgs) {
        return AnalysisService.analyze(analyzeSutArgs);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/time")
    public AnalyzeTime time(AnalyzeTime analyzeTime) {
        StressTestService.doStressTests(analyzeTime);
        return analyzeTime;
    }

}
