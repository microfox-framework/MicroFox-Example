package ir.moke.example;

import ir.moke.example.controller.AddressControllerAdd;
import ir.moke.example.controller.AddressControllerFind;
import ir.moke.example.controller.ClientControllerAdd;
import ir.moke.example.controller.ClientControllerList;
import ir.moke.example.persistence.DB;

import static ir.moke.microfox.MicroFox.httpGet;
import static ir.moke.microfox.MicroFox.httpPost;

public class MainClass {
    static {
        DB.initializeMyBatis();
        DB.initializeDatabase();
    }

    public static void main(String[] args) {
        httpPost("/api/v1/client", new ClientControllerAdd());
        httpGet("/api/v1/client", new ClientControllerList());
        httpPost("/api/v1/address/{clientId}", new AddressControllerAdd());
        httpGet("/api/v1/address", new AddressControllerFind());
    }
}
