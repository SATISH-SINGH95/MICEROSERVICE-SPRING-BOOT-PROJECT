package com.address.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {


    @NotNull(message = "Addreess Type should not be null")
    @NotBlank(message = "Addreess Type should not be blank")
    @Size(max = 15, message = "Addreess Type character limit is only 15")
    private String addreessType;

    @NotNull(message = "Addreess should not be null")
    @NotBlank(message = "Addreess should not be blank")
    @Size(max = 500, message = "Addreess character limit is only 500")
    private String address;

    @NotNull(message = "Addreess Type should not be null")
    @NotBlank(message = "Addreess Type should not be blank")
    @Size(max = 15, message = "Addreess Type character limit is only 15")
    private Long userId;

}
