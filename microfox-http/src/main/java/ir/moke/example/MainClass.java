package ir.moke.example;

import ir.moke.example.controller.ClientControllerAdd;
import ir.moke.example.controller.ClientControllerList;
import ir.moke.microfox.api.http.Response;

import static ir.moke.microfox.MicroFox.httpGet;
import static ir.moke.microfox.MicroFox.httpPost;

public class MainClass {

    public static void main(String[] args) {
        httpGet("/api/v1/sse", (request, response) -> sseEventProducer(response));
        httpPost("/api/v1/client", new ClientControllerAdd());
        httpGet("/api/v1/client", new ClientControllerList());
    }

    private static void sseEventProducer(Response response) {
        for (long i = 0; i < 10; i++) {
            response.sse("log", "Hello dear !", String.valueOf(i), 3000L);
            sleep();
        }
    }

    private static void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
