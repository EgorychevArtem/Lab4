package com.examples.Laba;

public class InputTestMessage {
    long packageId;
    String jsScript, functionName;
    InputTest test;

    InputTestMessage(long packageId, String jsScript, String functionName, InputTest test){
        this.packageId = packageId;
        this.jsScript = jsScript;
        this.functionName = functionName;
        this.test = test;
    }

    public long getPackageId() {
        return packageId;
    }

    public String getJsScript() {
        return jsScript;
    }

    public String getFunctionName() {
        return functionName;
    }

    public InputTest getTest() {
        return test;
    }
}
