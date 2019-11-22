package com.examples.Laba;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class InputPackage {
    long packageId;
    String jsScript, functionName;
    InputTest[] tests;

    @JsonCreator
    InputPackage(@JsonProperty("packageId") long packageId, @JsonProperty("jsScript") String jsScript,
                 @JsonProperty("functionName") String functionName, @JsonProperty("tests") InputTest[] tests){
        this.packageId = packageId;
        this.jsScript = jsScript;
        this.functionName = functionName;
        this.tests = tests;
    }

    public long getPackageId(){
        return packageId;
    }

    public String getJsScript(){
        return jsScript;
    }

    public String getFunctionName(){
        return functionName;
    }

    public InputTest[] getTests(){
        return tests;
    }


}
