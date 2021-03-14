package edu.baylor.ecs.cloudhubs.semantics.service;

import edu.baylor.ecs.cloudhubs.semantics.dto.AnalyzeSutArgs;
import edu.baylor.ecs.cloudhubs.semantics.dto.AnalyzedCache;
import edu.baylor.ecs.cloudhubs.semantics.util.file.MainService;

public class AnalysisService {

    public static AnalyzedCache analyze(AnalyzeSutArgs analyzeSutArgs) {
        MainService md = new MainService();
        return md.analyze(analyzeSutArgs);
    }
}
