package ir.moke.example.controller.dto;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(name = "ResponseClientDTO", description = "Client DTO")
public record ResponseClientDTO(
        @Schema(description = "Client's id", example = "id")
        Long id,
        @Schema(description = "Client's firs name", example = "Mahdi")
        String name,
        @Schema(description = "Client's last name", example = "Sheikh hosseini")
        String family,
        @ArraySchema(schema = @Schema(implementation = ResponseAddressDTO.class))
        List<ResponseAddressDTO> addresses) {
}
