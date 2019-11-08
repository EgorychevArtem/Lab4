package com.examples.Laba;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class InputTest {
    String testName, result;
    List<String> args;

    InputTest(@JsonProperty("testName") String testName, @JsonProperty("result") String result,
              @JsonProperty("args") List<String> args){
        this.testName = testName;
        this.result = result;
        this.args = args;
    }
}
