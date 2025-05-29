package ir.moke.example.controller.dto;

public record RequestAddressDTO(String street,
                                String city,
                                String state,
                                String country,
                                String postalCode,
                                String phoneNumber) {
}
