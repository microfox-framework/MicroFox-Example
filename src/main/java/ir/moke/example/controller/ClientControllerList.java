package ir.moke.example.controller;

import ir.moke.example.controller.dto.ResponseClientDTO;
import ir.moke.example.persistence.model.Client;
import ir.moke.example.service.AddressBookService;
import ir.moke.microfox.http.Request;
import ir.moke.microfox.http.Response;
import ir.moke.microfox.http.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ClientControllerList implements Route {
    private static final Logger logger = LoggerFactory.getLogger(ClientControllerList.class);

    @Override
    public void handle(Request request, Response response) {
        List<Client> allClients = AddressBookService.findAllClients();
        logger.info("Fetch all clients count: {}", allClients.size());
        List<ResponseClientDTO> list = allClients.stream().map(ControllerMapper::mapResponseClientDTO).toList();
        response.body(list);
    }
}
