package org.tiny.autounit.core.model;

import lombok.Data;

@Data
public class UnitParamData {

    private String className;

    private String newName;

    private boolean analysisFields = false;

}
