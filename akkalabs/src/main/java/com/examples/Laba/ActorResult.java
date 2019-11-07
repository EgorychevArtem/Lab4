package com.examples.Laba;

import akka.actor.AbstractActor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ActorResult {
    Map<String, ArrayList<Test>> storage = new HashMap<String, ArrayList<Test>>()
    public AbstractActor.Receive createReceive(){
        return receiveBuilder()
                .match(InputResMessage.class, m -> {
                    storage.
                })
    }
}
