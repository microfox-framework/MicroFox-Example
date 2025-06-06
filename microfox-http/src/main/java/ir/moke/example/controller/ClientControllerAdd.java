package ir.moke.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import ir.moke.example.Client;
import ir.moke.example.InMemoryDB;
import ir.moke.microfox.api.http.Request;
import ir.moke.microfox.api.http.Response;
import ir.moke.microfox.api.http.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientControllerAdd implements Route {
    private static final Logger logger = LoggerFactory.getLogger(ClientControllerAdd.class);

    @Override
    @Operation(
            summary = "Add new client",
            tags = "client",
            operationId = "add",
            requestBody = @RequestBody(
                    description = "Client DTO",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Client.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            description = "Successfully operation",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = Client.class)
                                    )
                            )
                    ),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500"),
                    @ApiResponse(description = "Invalid Input", responseCode = "400")
            }
    )
    public void handle(Request request, Response response) {
        Client client = request.body(Client.class);
        InMemoryDB.add(client);
        response.body(client);
    }
}
