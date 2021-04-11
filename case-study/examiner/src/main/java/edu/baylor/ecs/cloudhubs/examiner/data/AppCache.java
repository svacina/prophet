package edu.baylor.ecs.cloudhubs.examiner.data;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AppCache {
    private List<InconsistencyData> inconsistencyDataList;
    private List<CodeClonesData> codeClonesData;

    public AppCache(){
        this.inconsistencyDataList = new ArrayList<>();
        this.codeClonesData = new ArrayList<>();
    }

    public void addInconsistencyData(InconsistencyData inconsistencyData) {
        this.inconsistencyDataList.add(inconsistencyData);
    }

    public void addCodeClonesData(CodeClonesData codeClonesData) {
        this.codeClonesData.add(codeClonesData);
    }

}
