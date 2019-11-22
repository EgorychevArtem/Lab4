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
import akka.util.Timeout;
import scala.concurrent.duration.FiniteDuration;

import java.io.IOException;
import java.util.concurrent.CompletionStage;
//import java.util.concurrent.Future;
import scala.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import static akka.http.javadsl.server.Directives.path;
import static akka.http.javadsl.server.Directives.route;

public class App extends AllDirectives {
    private static String RESULT = "result";
    private static String PACKAGEID = "PackageId";
    private static String TEST = "test";

    public static void main(String[] args) throws IOException {
        ActorSystem system = ActorSystem.create("routes");
        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);

        App app = new App();

        Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = app.createRoute(system).flow(system, materializer);
        CompletionStage<ServerBinding> binding = http.bindAndHandle(
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

    Route createRoute(ActorSystem system) {
        ActorRef router = system.actorOf(ActorRouter.props());
        /*return route(
                path(RESULT, () -> {
                    return route(
                            get(() -> {
                                return parameter(PACKAGEID, pkg -> {
                                    Timeout time = Timeout.durationToTimeout(FiniteDuration.apply(5, TimeUnit.SECONDS));
                                    Future<Result> res = Patterns.ask(
                                            router, new OutputRes(pkg), time
                                    ).map(r -> (Result) r, system.dispatcher());
                                    return completeOKWithFuture(res, Jackson.marshaller());
                                });
                            })
                    );

                }),
                path(TEST, () ->
                        route(
                                post(()->
                                        entity(Jackson.unmarshaller(InputPackage.class), msg -> {
                                            router.tell(msg, ActorRef.noSender());
                                            return complete("Test start\n");
                                        }))
                        ))

        );*/
        return route (
                post(() ->
                        entity(Jackson.unmarshaller(InputPackage.class), msg -> {
                            router.tell(msg, ActorRef.noSender());
                            return complete("Test start\n");
                        })
                ),
                get(() ->
                        parameter(PACKAGEID, pkg -> {
                            Timeout time = Timeout.durationToTimeout(FiniteDuration.apply(5, TimeUnit.SECONDS));
                            Future<Object> result = Patterns.ask(
                                    router,
                                    new OutputRes(Long.parseLong(pkg)),
                                    time
                            );
                            return completeOKWithFuture(result,Jackson.marshaller());
                        })
                )
        );
    }
}