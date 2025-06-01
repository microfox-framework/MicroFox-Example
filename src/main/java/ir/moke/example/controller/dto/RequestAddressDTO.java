package ir.moke.example.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Schema(name = "RequestAddressDTO", description = "Address DTO")
public record RequestAddressDTO(
        @Schema(description = "Address's country", example = "Iran") @NotEmpty @NotNull
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
