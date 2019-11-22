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

    public static Props props(){
        return Props.create(ActorPerform.class);
    }
   /* ActorPerform(ActorRef storage) {
        this.storage = storage;
    }*/

    private String checkTest(String script, String NameFunction, Object... args) throws ScriptException, NoSuchMethodException {
        ScriptEngine engine = new
                ScriptEngineManager().getEngineByName("nashorn");
        engine.eval(script);
        Invocable invocable = (Invocable) engine;
        return invocable.invokeFunction(NameFunction, args).toString();
    }

    public Receive createReceive() {
        return receiveBuilder()
                /*.match(InputTestMessage.class, m-> {
                    boolean resflag = false;
                    String description;
                    try {
                        String out = checkTest(m.script, m.NameFunction, m.args);
                        resflag = out.equals(m.res);
                        description = resflag ? "ok" : "Expected: " + m.res + "Out: " + out;
                    } catch (ScriptException e){
                        description = "ScriptException\n" + e.getMessage();
                    } catch (NoSuchMethodException e){
                        description = "NoSuchMethodException\n" + e.getMessage();
                    }
                    this.storage.tell(new InputResMessage(m.pkg, new Test(m.name, resflag)),getSelf());
                }).build();*/
                .match(InputTestMessage.class, m->{
                    getSender().tell(
                            new InputResMessage(m.packageId, m.test, runTest(m)),
                            ActorRef.noSender()
                    );
                }).build();
    }

    public static String  runTest(InputTestMessage m){
        try{
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
            engine.eval(m.jsScript);
            Invocable invocable = (Invocable) engine;
            return invocable.invokeFunction(m.getFunctionName(), m.test.params).toString();
        } catch (ScriptException e) {
            return "ScriptException\n" + e.getMessage();
        } catch (NoSuchMethodException e){
            return "NoSuchMethodException\n" + e.getMessage();
        }
    }
}
