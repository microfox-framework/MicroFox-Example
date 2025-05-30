package ir.moke.example.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "RequestClientDTO", description = "Client DTO")
public record RequestClientDTO(
        @Schema(description = "Client's firs name", example = "Mahdi")
        String name,
        @Schema(description = "Client's last name", example = "Sheikh hosseini")
        String family) {
}
