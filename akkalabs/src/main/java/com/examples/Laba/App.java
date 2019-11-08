package com.examples.Laba;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;

import java.io.IOException;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Future;
import java.util.regex.Pattern;

import static akka.http.javadsl.server.Directives.path;
import static akka.http.javadsl.server.Directives.route;

public class App extends AllDirectives {
    public static void main(String[] args) throws IOException {
        ActorSystem system = ActorSystem.create("routers");
        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);

        //MainHttp instance = new MainHttp(system);
        App app = new App();
        ActorRef router = system.actorOf(Props.create(ActorRouter.class, 5));
        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow =
                app.createRoute(system).flow(system, materializer);
        final CompletionStage<ServerBinding> binding = http.bindAndHandle(
                routeFlow,
                ConnectHttp.toHost("localhost", 8080),
                materializer
        );
        System.out.println("Server online at http://localhost:8080/\nPress RETURN to stop...");
        System.in.read();
        binding
                .thenCompose(ServerBinding::unbind)
                .thenAccept(unbound -> system.terminate());
    }

    Route createRoute(ActorSystem system, ActorRef router) {
        return route(
                path("result", () -> {
                    return route(
                            get(() -> {
                                return parameter("packageId", pkg -> {
                                    Future<Result> res = Patterns.ask(
                                            router, new OutputRes(pkg),
                                    ).map(r -> r, system.dispatcher());
                                    return completeOKWithFuture(res, Jackson.marshaller());
                                });
                            })
                    );

                })
        )
    }
}