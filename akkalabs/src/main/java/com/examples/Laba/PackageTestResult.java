package com.examples.Laba;

import java.util.List;

public class PackageTestResult {
    long pkg;
    List<Test> results;

    public PackageTestResult(long pkg, List<Test> results){
        this.pkg = pkg;
        this.results = results;
    }
}
