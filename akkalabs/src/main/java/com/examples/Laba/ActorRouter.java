package com.examples.Laba;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.routing.RoundRobinPool;

public class ActorRouter extends AbstractActor {
    ActorRef storage, router;

    ActorRouter(){
        this.storage = getContext().actorOf(Props.create(ActorResult.class));
        this.router = getContext().actorOf(Props.create(ActorPerform.class, this.storage)
        .withRouter(new RoundRobinPool()));
    }

    @Override
    public Receive createReceive() {
        return null;
    }

}
