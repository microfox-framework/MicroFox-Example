package ir.moke.example;

import ir.moke.example.controller.ClientControllerAdd;
import ir.moke.example.controller.ClientControllerList;

import static ir.moke.microfox.MicroFox.httpGet;
import static ir.moke.microfox.MicroFox.httpPost;

public class RestRunner {

    public static void main(String[] args) {
        httpPost("/api/v1/client", new ClientControllerAdd());
        httpGet("/api/v1/client", new ClientControllerList());
    }
}
