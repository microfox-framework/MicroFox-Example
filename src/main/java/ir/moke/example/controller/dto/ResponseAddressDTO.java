package ir.moke.example.controller.dto;

public record ResponseAddressDTO(Long id,
                                 String street,
                                 String city,
                                 String state,
                                 String country,
                                 String postalCode,
                                 String phoneNumber) {

}