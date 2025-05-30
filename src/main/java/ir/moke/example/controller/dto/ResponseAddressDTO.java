package ir.moke.example.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ResponseAddressDTO", description = "Address DTO")
public record ResponseAddressDTO(
        @Schema(description = "Address's id", example = "123")
        Long id,

        @Schema(description = "Address's country", example = "Iran")
        String country,
        @Schema(description = "Address's state", example = "Tehran")
        String state,
        @Schema(description = "Address's city", example = "Tehran")
        String city,
        @Schema(description = "Address's street", example = "Fakhr Razi")
        String street,
        @Schema(description = "Address's postalCode", example = "0987654321")
        String postalCode,
        @Schema(description = "Address's postalCode", example = "+219988776655")
        String phoneNumber) {

}