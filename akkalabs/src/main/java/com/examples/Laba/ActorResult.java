package com.examples.Laba;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ActorResult extends AbstractActor {
    Map<Long, ArrayList<Test>> storage = new TreeMap<>();

    public static Props props() {
        return Props.create(ActorResult.class, ActorResult::new);
    }

    @Override
    public Receive createReceive(){
        return receiveBuilder()
                .match(InputResMessage.class, m-> {
                    this.storage.computeIfAbsent(m.pkg, k-> new ArrayList<>())
                            .add(m.result);
                })
                .match(OutputRes.class, m->{
                    getSender().tell(
                         new Result(m.getPackageId(), this.storage.get(m.getPackageId())),
                            ActorRef.noSender()
                    );
                })
                .build();
              /*  .match(InputResMessage.class, m -> {
                    if(storage.containsKey(m.pkg)) {
                        storage.get(m.pkg).add(m.test);
                    } else {
                      ArrayList<Test> tests = new ArrayList<>();
                      tests.add(m.test);
                      storage.put(m.pkg,tests);
                    }
                })
                .match(String.class, m -> {
                    ArrayList<Test> list = storage.getOrDefault(m, new ArrayList<>());
                    Test[] arr = new Test[list.size()];
                    arr = list.toArray(arr);
                    sender().tell(
                            new Result(arr),
                            getSelf()
                    );
                }).build();*/
    }
}
