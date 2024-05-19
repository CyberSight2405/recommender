package com.hakaton.recommender.service;

import com.hakaton.recommender.grpc.RecommendationServiceGrpc;
import com.hakaton.recommender.grpc.RecommendationServiceOuterClass;
import io.grpc.stub.StreamObserver;

import java.util.Random;

public class RecommendationServiceIml extends RecommendationServiceGrpc.RecommendationServiceImplBase {

    private static String[] RECOMMENDATIONS = new String[] {
            "Персональное предложение ддя {username}",
            "Делай утреннюю зарядку",
            "Следи за новостями мира ИТ"
    };

    private final Random RANDOM = new Random();

    @Override
    public void get(RecommendationServiceOuterClass.RecommendationRequest request,
                    StreamObserver<RecommendationServiceOuterClass.RecommendationResponse> responseObserver) {

        RecommendationServiceOuterClass.RecommendationResponse response =
                RecommendationServiceOuterClass.RecommendationResponse.newBuilder()
                        .setRecommendation(getRandomRecommendation(request.getUsername()))
                        .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private String getRandomRecommendation(String username) {
        int ind = getRandomInRange(0, RECOMMENDATIONS.length);
        return RECOMMENDATIONS[ind].replaceAll("\\{username\\}", username);
    }

    private int getRandomInRange(int lower, int upper) {
        int intervalLength = upper - lower;
        return RANDOM.nextInt(intervalLength) + lower;
    }

}
