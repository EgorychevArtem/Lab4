package com.examples.Laba;

import java.util.List;

public class Result {
    long packageId;
    List<Test> results;
    public Result(long packageId, List<Test> results) {
        this.packageId = packageId;
        this.results = results;
    }
}


/*public class Result {
    long packageId;
    List<Test> results;

    public Result(long packageId, List<Test> results) {
        this.packageId = packageId;
        this.results = results;
    }

    public long getPackageId() {
        return packageId;
    }

    public List<Test> getResults() {
        return results;
    }
}
*/