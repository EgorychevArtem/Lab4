package com.examples.Laba;

public class InputTestMessage {
    String pkg, name, script, NameFunction, res;
    Object[] args;

    InputTestMessage(String pkg, String name, String script, String NameFunction, String res, Object[] args){
        this.pkg = pkg;
        this.name = name;
        this.script = script;
        this.NameFunction = NameFunction;
        this.res = res;
        this.args = args;
    }
}
