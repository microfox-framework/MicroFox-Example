package ir.moke.example.controller;

import ir.moke.example.controller.dto.RequestAddressDTO;
import ir.moke.example.persistence.model.Address;
import ir.moke.example.service.AddressBookService;
import ir.moke.microfox.http.Request;
import ir.moke.microfox.http.Response;
import ir.moke.microfox.http.Route;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddressControllerAdd implements Route {
    private static final Logger logger = LoggerFactory.getLogger(AddressControllerAdd.class);

    @Override
    public void handle(Request request, Response response) {
        String id = request.pathParam("clientId");
        if (id == null || id.isEmpty()) {
            response.status(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            RequestAddressDTO requestAddressDTO = request.body(RequestAddressDTO.class);
            Address address = ControllerMapper.mapToAddress(requestAddressDTO);
            AddressBookService.saveAddress(address, Long.parseLong(id));

            response.body(ControllerMapper.mapToResponseAddressDTO(address));
        } catch (Exception e) {
            response.body(e.getMessage());
        }
    }
}
