package com.address.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.address.exception.ErrorResponse;
import com.address.model.request.AddressRequest;
import com.address.model.response.AddressResponse;
import com.address.service.AddressService;

import java.net.HttpURLConnection;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/addresses")
@Tag(name = "Users", description = "Users endpoints")
@ApiResponses(value = { @ApiResponse(responseCode = HttpURLConnection.HTTP_NOT_FOUND
                + "", description = "Not Found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                @ApiResponse(responseCode = HttpURLConnection.HTTP_BAD_REQUEST
                                + "", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                @ApiResponse(responseCode = HttpURLConnection.HTTP_INTERNAL_ERROR
                                + "", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponse.class))) })
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/")
    @Operation(summary = "Create Address", description = "Endpoint to create Address")
	@ApiResponse(responseCode = HttpURLConnection.HTTP_CREATED + "", description = "CREATED", content = {
		@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = AddressResponse.class)) })
    public ResponseEntity<AddressResponse> saveAddress(
        @Parameter(name = "AddressRequest", description = "AddressRequest is required", required = true, schema = @Schema(implementation = AddressRequest.class)) @RequestBody @Valid AddressRequest addressRequest){
        AddressResponse response = addressService.saveAddress(addressRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/addressId/{AddressId}")
    @Operation(summary = "Get single address by address-Id", description = "Endpoint to Get single address by address-Id")
	@ApiResponse(responseCode = HttpURLConnection.HTTP_OK + "", description = "OK", content = {
		@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = AddressResponse.class)) })
    public ResponseEntity<AddressResponse> getAddressByAddressId(
        @Parameter(name = "AddressId", description = "AddressId is required", required = true, schema = @Schema(implementation = Long.class)) @PathVariable Long AddressId){
        AddressResponse response = addressService.getAddressbyAddressId(AddressId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/adddress/userId/{userId}")
    @Operation(summary = "Get single address by user-Id", description = "Endpoint to Get single address by user-Id")
	@ApiResponse(responseCode = HttpURLConnection.HTTP_OK + "", description = "OK", content = {
		@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = AddressResponse.class)) })
    public ResponseEntity<List<AddressResponse>> getAddressByUserId(
        @Parameter(name = "userId", description = "userId is required", required = true, schema = @Schema(implementation = Long.class)) @PathVariable Long userId){
        List<AddressResponse> responses = addressService.getAddressesByUserId(userId);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }



}
