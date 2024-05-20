package com.user.model.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestObject {

    @NotNull(message = "Name should not be null")
    @NotBlank(message = "Name should not be blank")
    @Size(max = 126, min = 3, message = "Name character should be between 3 to 126")
    private String name;

    @NotNull(message = "companyName should not be null")
    @NotBlank(message = "companyName should not be blank")
    @Size(max = 126, min = 3, message = "companyName character should be between 3 to 126")
    private String companyName;

    @Max(value = 999999L, message = "Employee Code has maximum 6 digit limit")
    @Min(value = 999L, message = "Employee Code has minimum 3 digit limit")
    private Long empCode;

}
