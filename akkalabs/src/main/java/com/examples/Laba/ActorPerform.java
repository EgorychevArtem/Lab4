package com.examples.Laba;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ActorPerform extends AbstractActor{
    ActorRef storage;

    ActorPerform(ActorRef storage) {
        this.storage = storage;
    }

    private String checkTest(InputTestMessage m) throws ScriptException, NoSuchMethodException {
        try{
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
            engine.eval(m.jsScript);
            Invocable invocable = (Invocable) engine;
            return invocable.invokeFunction(m.functionName, m.test.params).toString();
        } catch (ScriptException e) {
            return "ScriptException\n" + e.getMessage();
        } catch (NoSuchMethodException e){
            return "NoSuchMethodException\n" + e.getMessage();
        }
    }

    public Receive createReceive() {
        return receiveBuilder()
                .match(InputTestMessage.class, m->{
                    getSender().tell(
                            new InputResMessage(m.packageId, m.test,checkTest(m)),
                            ActorRef.noSender()
                    );
                }).build();
    }
}
