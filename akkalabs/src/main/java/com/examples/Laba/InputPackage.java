package com.examples.Laba;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class InputPackage {
    String packageId, script, Namefunction;
    InputTest[] tests;

    @JsonCreator
    InputPackage(@JsonProperty("packageId") String packageId, @JsonProperty("jsScript") String script,
                 @JsonProperty("functionName") String Namefunction, @JsonProperty("tests") InputTest[] tests){
        this.packageId = packageId;
        this.script = script;
        this.Namefunction = Namefunction;
        this.tests = tests;
    }
}
