package ir.moke.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import ir.moke.example.controller.dto.ResponseAddressDTO;
import ir.moke.example.persistence.model.Address;
import ir.moke.example.service.AddressBookService;
import ir.moke.microfox.http.Request;
import ir.moke.microfox.http.Response;
import ir.moke.microfox.http.Route;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddressControllerFind implements Route {
    private static final Logger logger = LoggerFactory.getLogger(AddressControllerFind.class);

    @Override
    @Operation(
            summary = "Find address",
            tags = "address",
            operationId = "find",
            responses = {
                    @ApiResponse(
                            description = "Successfully operation",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = ResponseAddressDTO.class)
                                    )
                            )
                    ),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500"),
                    @ApiResponse(description = "Invalid Input", responseCode = "400")
            },
            parameters = {
                    @Parameter(
                            name = "id",
                            example = "123",
                            description = "Id of address",
                            in = ParameterIn.QUERY,
                            required = true,
                            schema = @Schema(type = "string")
                    )
            }
    )
    public void handle(Request request, Response response) {
        try {
            String id = request.queryParameter("id");
            Address address = AddressBookService.findAddress(Long.parseLong(id));
            if (address != null) {
                response.body(ControllerMapper.mapToResponseAddressDTO(address));
            } else {
                response.status(HttpServletResponse.SC_NO_CONTENT);
            }
        } catch (NumberFormatException nfe) {
            response.status(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
