package ir.moke.example.controller;

import ir.moke.example.controller.dto.RequestAddressDTO;
import ir.moke.example.controller.dto.RequestClientDTO;
import ir.moke.example.controller.dto.ResponseAddressDTO;
import ir.moke.example.controller.dto.ResponseClientDTO;
import ir.moke.example.persistence.model.Address;
import ir.moke.example.persistence.model.Client;

import java.util.List;

public class DTOMapper {

    public static Address mapToAddress(RequestAddressDTO dto) {
        return new Address(dto.street(), dto.city(), dto.state(), dto.country(), dto.postalCode());
    }

    public static ResponseAddressDTO mapToResponseAddressDTO(Address address) {
        return new ResponseAddressDTO(
                address.getId(),
                address.getStreet(),
                address.getCity(),
                address.getState(),
                address.getCountry(),
                address.getPostalCode(),
                address.getPhoneNumber()
        );
    }

    public static Client mapToClient(RequestClientDTO dto) {
        return new Client(dto.name(), dto.family(), null);
    }

    public static ResponseClientDTO mapResponseClientDTO(Client client) {
        List<ResponseAddressDTO> responseAddressDTOS = client.getAddresses() != null ? client.getAddresses().stream().map(DTOMapper::mapToResponseAddressDTO).toList() : List.of();
        return new ResponseClientDTO(client.getId(), client.getName(), client.getFamily(), responseAddressDTOS);
    }
}
