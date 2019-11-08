package com.examples.Laba;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class InputPackage {
    String packageId, script, Namefunction;
    List<InputTest> tests;

    InputPackage(@JsonProperty("packageId") String packageId, @JsonProperty("script") String script,
                 @JsonProperty("Namefunction") String Namefunction, @JsonProperty("tests") List<InputTest> tests){
        this.packageId = packageId;
        this.script = script;
        this.Namefunction = Namefunction;
        this.tests = tests;
    }
}
