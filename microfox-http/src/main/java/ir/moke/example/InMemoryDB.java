package ir.moke.example;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDB {
    private static final List<Client> CLIENT_LIST = new ArrayList<>();

    public static void add(Client client) {
        CLIENT_LIST.add(client);
    }

    public static List<Client> list() {
        return CLIENT_LIST;
    }
}
