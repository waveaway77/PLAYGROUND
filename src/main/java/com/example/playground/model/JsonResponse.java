package com.example.playground.model;

public class JsonResponse {
    private String successYn = "";

    public String getSuccessYn() {
        return successYn;
    }

    public void setSuccessYn(String successYn) {
        this.successYn = successYn;
    }

    @Override
    public String toString() {
        return "JsonResponse{" +
                "successYn='" + successYn + '\'' +
                '}';
    }
}
