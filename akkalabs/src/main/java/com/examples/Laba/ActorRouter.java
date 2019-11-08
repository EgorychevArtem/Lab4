package com.examples.Laba;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.routing.RoundRobinPool;

public class ActorRouter extends AbstractActor {
    ActorRef storage, router;

    ActorRouter(int n){
        this.storage = getContext().actorOf(Props.create(ActorResult.class));
        this.router = getContext().actorOf(Props.create(ActorPerform.class, this.storage)
        .withRouter(new RoundRobinPool(n)));
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(InputPackage.class, m -> {
                    
                })
    }

}
