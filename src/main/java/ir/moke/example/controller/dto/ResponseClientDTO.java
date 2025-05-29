package ir.moke.example.controller.dto;

import java.util.List;

public record ResponseClientDTO(Long id,
                                String name,
                                String family,
                                List<ResponseAddressDTO> addresses) {
}
