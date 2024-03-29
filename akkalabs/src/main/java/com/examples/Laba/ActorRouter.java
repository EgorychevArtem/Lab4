package com.examples.Laba;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.routing.RoundRobinPool;

import java.util.stream.Stream;

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
                .match(OutputRes.class, m->{
                    this.storage.tell(m, getSender());
                })
                .match(InputPackage.class, m->{
                    Stream.of(m.tests).map(t -> new InputTestMessage(m.packageId, m.jsScript, m.functionName, t))
                    .forEach(msg -> this.router.tell(msg, this.storage));
                })
                .build();
    }
}
