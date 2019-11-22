package com.examples.Laba;

import java.util.List;

public class InputResMessage {
    public long packageId;
    public Test result;
    InputResMessage(long packageId, Test result){
        this.packageId = packageId;
        this.result = result;
    }

    public InputResMessage(long packageId, InputTest test, String result){
        this(packageId, new Test(test, result));
    }

    public long getPackageId(){
        return packageId;
    }

    public Test getResult(){
        return result;
    }
}
