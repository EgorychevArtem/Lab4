package com.examples.Laba;

import akka.actor.AbstractActor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ActorResult extends AbstractActor {
    Map<Long, ArrayList<Test>> storage = new HashMap<>();

    @Override
    public Receive createReceive(){
        return receiveBuilder()
                .match(InputResMessage.class, m-> {
                    this.storage.computeIfAbsent(m.pkg, k-> new ArrayList<>())
                            .add(m.test);
                })
                .match(OutputRes.class, m->{

                })
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
