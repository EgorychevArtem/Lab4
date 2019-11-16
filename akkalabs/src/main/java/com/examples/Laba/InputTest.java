package com.examples.Laba;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class InputTest {
    String testName, result;
    List<String> args;

    @JsonCreator
    InputTest(@JsonProperty("testName") String testName, @JsonProperty("expectedResult") String result,
              @JsonProperty("params") List<String> args){
        this.testName = testName;
        this.result = result;
        this.args = args;
    }
}
