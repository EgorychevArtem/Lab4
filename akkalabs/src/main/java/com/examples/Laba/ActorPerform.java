package com.examples.Laba;

import akka.actor.ActorRef;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ActorPerform {
    ActorRef storage;

    ActorPerform(ActorRef storage) {
        this.storage = storage;
    }

    String checkTest(String script, String NameFunction, String[] args) throws ScriptException, NoSuchMethodException {
        ScriptEngine engine = new
                ScriptEngineManager().getEngineByName("nashorn");
        engine.eval(script);
        Invocable invocable = (Invocable) engine;
        return invocable.invokeFunction(NameFunction, args).toString();
    }
}
