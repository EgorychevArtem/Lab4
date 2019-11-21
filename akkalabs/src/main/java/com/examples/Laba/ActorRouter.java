package com.examples.Laba;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.routing.RoundRobinPool;

public class ActorRouter extends AbstractActor {
    ActorRef storage, router;

    public static Props props(){
        return Props.create(ActorRouter.class, ActorRouter::new);
    }

    ActorRouter(){
        this.storage = getContext().actorOf(ActorResult.props());
        this.router = getContext().actorOf()
    }

    /*ActorRouter(int n){
        this.storage = getContext().actorOf(Props.create(ActorResult.class));
        this.router = getContext().actorOf(Props.create(ActorPerform.class, this.storage)
        .withRouter(new RoundRobinPool(n)));
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(InputPackage.class, m -> {
                    for(InputTest test : m.tests){
                        this.router.tell(
                                new InputTestMessage(
                                        m.packageId, m.script,
                                        m.Namefunction, test),
                                getSelf()
                        );
                    }
                })
                .match(OutputRes.class, m-> this.storage.tell(m.pkg, sender()))
                .build();
    }*/

}
