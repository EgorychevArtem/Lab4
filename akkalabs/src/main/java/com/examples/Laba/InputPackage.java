package com.examples.Laba;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class InputPackage {
    long packageId;
    String jsScript, functionName;
    InputTest[] tests;

    @JsonCreator
    InputPackage(@JsonProperty("packageId") long packageId, @JsonProperty("jsScript") String script,
                 @JsonProperty("functionName") String Namefunction, @JsonProperty("tests") InputTest[] tests){
        this.packageId = packageId;
        this.jsScript = script;
        this.functionName = Namefunction;
        this.tests = tests;
    }
}
