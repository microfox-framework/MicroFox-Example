package ir.moke.example;

import ir.moke.example.controller.ClientControllerAdd;
import ir.moke.example.controller.ClientControllerList;
import ir.moke.microfox.api.http.sse.SseObject;

import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static ir.moke.microfox.MicroFox.*;

public class MainClass {

    public static void main(String[] args) {
        sseRegister("H1", "/sse/hello");
        httpPost("/api/v1/client", new ClientControllerAdd());
        httpGet("/api/v1/client", new ClientControllerList());

        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> ssePublisher("H1", () -> new SseObject("Hello " + LocalTime.now())), 0, 2, TimeUnit.SECONDS);
    }
}
