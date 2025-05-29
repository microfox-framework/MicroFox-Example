package ir.moke.example.controller;

import ir.moke.example.controller.dto.RequestAddressDTO;
import ir.moke.example.persistence.model.Address;
import ir.moke.example.service.AddressBookService;
import ir.moke.microfox.http.Request;
import ir.moke.microfox.http.Response;
import ir.moke.microfox.http.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddressControllerFind implements Route {
    private static final Logger logger = LoggerFactory.getLogger(AddressControllerFind.class);

    @Override
    public void handle(Request request, Response response) {
        String id = request.queryParameter("id");

        Address address = AddressBookService.findAddress(Long.parseLong(id));

        response.body(ControllerMapper.mapToResponseAddressDTO(address));
    }
}
