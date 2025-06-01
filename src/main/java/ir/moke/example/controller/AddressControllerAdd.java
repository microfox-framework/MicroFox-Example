package ir.moke.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import ir.moke.example.controller.dto.RequestAddressDTO;
import ir.moke.example.controller.dto.ResponseAddressDTO;
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
    @Operation(
            summary = "Add new address",
            tags = "address",
            operationId = "add",
            requestBody = @RequestBody(
                    description = "Address DTO",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RequestAddressDTO.class)
                    )
            ),
            parameters = {
                    @Parameter(
                            name = "clientId",
                            example = "123",
                            description = "Id of client",
                            in = ParameterIn.PATH,
                            required = true,
                            schema = @Schema(type = "string")
                    )
            },
            responses = {
                    @ApiResponse(
                            description = "Successfully operation",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseAddressDTO.class)
                            )
                    ),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500"),
                    @ApiResponse(description = "Invalid Input", responseCode = "400")
            }
    )
    public void handle(Request request, Response response) {
        String id = request.pathParam("clientId");
        if (id == null || id.isEmpty()) {
            response.status(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        RequestAddressDTO requestAddressDTO = request.body(RequestAddressDTO.class);
        Address address = DTOMapper.mapToAddress(requestAddressDTO);
        AddressBookService.saveAddress(address, Long.parseLong(id));

        response.body(DTOMapper.mapToResponseAddressDTO(address));
    }
}
