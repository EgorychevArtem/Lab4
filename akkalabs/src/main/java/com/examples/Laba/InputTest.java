package com.examples.Laba;

/*import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class InputTest {
    String testName, expectedResult;
    Object[] params;

    @JsonCreator
    InputTest(@JsonProperty("testName") String testName, @JsonProperty("expectedResult") String expectedResult,
              @JsonProperty("params") Object[] params){
        this.testName = testName;
        this.expectedResult = expectedResult;
        this.params = params;
    }
}
*/

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class InputTest {
    String testName;
    String expectedResult;
    Object[] params;

    @JsonCreator
    public InputTest(
            @JsonProperty("testName") String testName,
            @JsonProperty("expectedResult") String expectedResult,
            @JsonProperty("params") Object[] params
    ) {
        this.testName = testName;
        this.expectedResult = expectedResult;
        this.params = params;
    }

    public String getTestName(){
        return testName;
    }

    public String getExpectedResult(){
        return expectedResult;
    }

    public Object[] getParams(){
        return params;
    }
}