package com.examples.Laba;

import com.fasterxml.jackson.annotation.JsonCreator;
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
