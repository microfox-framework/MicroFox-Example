package ir.moke.example.controller;

import ir.moke.example.controller.dto.RequestClientDTO;
import ir.moke.example.persistence.model.Client;
import ir.moke.example.service.AddressBookService;
import ir.moke.microfox.http.Request;
import ir.moke.microfox.http.Response;
import ir.moke.microfox.http.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientControllerAdd implements Route {
    private static final Logger logger = LoggerFactory.getLogger(ClientControllerAdd.class);

    @Override
    public void handle(Request request, Response response) {
        RequestClientDTO dto = request.body(RequestClientDTO.class);
        Client client = ControllerMapper.mapToClient(dto);
        AddressBookService.saveClient(client);
        logger.info("Add new client with id: {}", client.getId());
        response.body(ControllerMapper.mapResponseClientDTO(client));
    }
}
