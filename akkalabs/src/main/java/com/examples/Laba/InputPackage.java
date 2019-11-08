package com.examples.Laba;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class InputPackage {
    String packageId, script, Namefunction;
    List<InputTest> tests;

    InputPackage(@JsonProperty("testName") String testName)
}
