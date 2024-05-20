package com.user.controller;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.exception.ErrorResponse;
import com.user.model.request.UserRequestObject;
import com.user.model.response.UserResponseObject;
import com.user.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@Slf4j
@RequestMapping("/users")
@Tag(name = "Users", description = "Users endpoints")
@ApiResponses(value = { @ApiResponse(responseCode = HttpURLConnection.HTTP_NOT_FOUND
                + "", description = "Not Found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                @ApiResponse(responseCode = HttpURLConnection.HTTP_BAD_REQUEST
                                + "", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                @ApiResponse(responseCode = HttpURLConnection.HTTP_INTERNAL_ERROR
                                + "", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponse.class))) })
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @param userRequestObject
     * @return userResponseObject
     */
    @PostMapping("/create")
    @Operation(summary = "Create User", description = "Endpoint to create User")
	@ApiResponse(responseCode = HttpURLConnection.HTTP_CREATED + "", description = "CREATED", content = {
		@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserResponseObject.class)) })
    public ResponseEntity<UserResponseObject> createUser(
        @Parameter(name = "UserRequestObject", description = "UserRequestObject is required", required = true, schema = @Schema(implementation = UserRequestObject.class)) @RequestBody @Valid UserRequestObject userRequestObject){
        UserResponseObject response = userService.createUser(userRequestObject);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * @return List<UserResponseObject>
     */
    @GetMapping("/")
    @Operation(summary = "Get All user detail", description = "Endpoint to Get all user Detail")
	@ApiResponse(responseCode = HttpURLConnection.HTTP_OK + "", description = "OK", content = {
		@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = List.class)) })
    public ResponseEntity<List<UserResponseObject>> getAllUsers(
        @Parameter(name = "pageNumber", description = "pageNumber is not mandatory", in = ParameterIn.QUERY, required = false, schema = @Schema(implementation = Integer.class)) @Valid @Min(value = 0, message = "Minimum Page number should be 0") Integer pageNumber,
        @Parameter(name = "pageSize", description = "pageSize is not mandatory", in = ParameterIn.QUERY, required = false, schema = @Schema(implementation = Integer.class)) @Valid @Min(value = 1, message = "Minimum Page size should be 1") Integer pageSize,
        @Parameter(name = "sortingField", description = "sortingField is mandatory", in = ParameterIn.QUERY, required = false, schema = @Schema(implementation = String.class)) @Valid String sortingField,
        @Parameter(name = "sortingOrder", description = "sortingOrder is mandatory", in = ParameterIn.QUERY, required = false, schema = @Schema(implementation = String.class)) @Valid String sortingOrder
    ){

        // SortingFieldEnum sortingFieldEnum = SortingFieldEnum.valueOf(sortingField.toUpperCase());
        // OrderByEnum orderByEnum = OrderByEnum.valueOf(sortingOrder.toUpperCase());
        List<UserResponseObject> allUser = userService.getAllUser(pageNumber, pageSize, sortingField, sortingOrder);
        return new ResponseEntity<>(allUser, HttpStatus.OK);
    }

    /**
     * @param userId
     * @return UserResponseObject
     */
    @GetMapping("/userId/{userId}")
    @Operation(summary = "Get single user detail", description = "Endpoint to Get single user Detail")
	@ApiResponse(responseCode = HttpURLConnection.HTTP_OK + "", description = "OK", content = {
		@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserResponseObject.class)) })
    public ResponseEntity<UserResponseObject> getSingleUser(
        @Parameter(name = "userId", description = "userId is required", required = true, schema = @Schema(implementation = Long.class)) @PathVariable Long userId){
        log.debug("getSingleUser starts | user-id = {}", userId);
        UserResponseObject response = userService.getUserById(userId);
        log.debug("getSingleUser end | response = {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
