package com.examples.Laba;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ActorPerform extends AbstractActor{
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

    public AbstractActor.Receive createReceive() {
        return receiveBuilder()
                .match(InputTestMessage.class, m-> {
                    boolean resflag;
                    String description;
                    String out = checkTest(m.script, m.NameFunction, m.args);
                    if (out == m.res){
                        resflag = true;
                    } else{
                        resflag = false;
                    }
                    description = resflag ? "ok" : "Expected: " + m.res + "Out: " + out;
                    this.storage.tell(new InputResMessage(m.pkg, new Test(m.name, resflag, description)),getSelf());
                }).build();
    }
}
