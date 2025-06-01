package ir.moke.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import ir.moke.example.Client;
import ir.moke.example.InMemoryDB;
import ir.moke.microfox.http.Request;
import ir.moke.microfox.http.Response;
import ir.moke.microfox.http.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ClientControllerList implements Route {
    private static final Logger logger = LoggerFactory.getLogger(ClientControllerList.class);

    @Override
    @Operation(
            summary = "List clients",
            tags = "client",
            operationId = "list",
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
        List<Client> list = InMemoryDB.list();
        logger.info("Fetch all clients count: {}", list.size());
        response.body(list);
    }
}
