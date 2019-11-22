package com.examples.Laba;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ActorResult extends AbstractActor {
    Map<Long, ArrayList<Test>> storage = new HashMap<>();

    @Override
    public Receive createReceive(){
        return receiveBuilder()
                .match(InputResMessage.class, m-> {
                    this.storage.computeIfAbsent(m.packageId, k-> new ArrayList<>())
                            .add(m.result);
                })
                .match(OutputRes.class, m->{
                    getSender().tell(
                         new Result(m.getPackageId(), this.storage.get(m.getPackageId())),
                            ActorRef.noSender()
                    );
                })
                .build();
    }
}
