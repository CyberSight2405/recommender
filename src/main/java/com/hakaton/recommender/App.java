package com.hakaton.recommender;

import com.hakaton.recommender.service.RecommendationServiceIml;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class App
{
    public static void main( String[] args ) throws IOException, InterruptedException {
        Server server = ServerBuilder
                .forPort(8180)
                .addService(new RecommendationServiceIml())
                .build();

        server.start();
        server.awaitTermination();
    }
}
