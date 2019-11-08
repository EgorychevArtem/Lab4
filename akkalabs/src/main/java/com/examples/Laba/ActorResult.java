package com.examples.Laba;

import akka.actor.AbstractActor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ActorResult extends AbstractActor {
    Map<String, ArrayList<Test>> storage = new HashMap<String, ArrayList<Test>>();
    public Receive createReceive(){
        return receiveBuilder()
                .match(InputResMessage.class, m -> {
                    storage.get(m.pkg).add(m.test);
                })
                .match(String.class, m -> {
                    ArrayList<Test> list = storage.getOrDefault(m, new ArrayList<Test>());
                    Test[] arr = new Test[list.size()];
                    arr = list.toArray(arr);
                    sender().tell(
                            new Result(arr),
                            getSelf()
                    );
                }).build();
    }
}
