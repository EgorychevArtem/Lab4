package com.examples.Laba;

import akka.actor.ActorSystem;
import akka.http.javadsl.Http;

public class App {
    public static void main(String[] args){
        ActorSystem system = ActorSystem.create("routers");
        final Http http = Http.get(system);
        
    }
}
