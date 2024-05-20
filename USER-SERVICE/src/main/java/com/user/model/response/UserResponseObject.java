package com.user.model.response;

import java.util.List;
import lombok.Data;

@Data
public class UserResponseObject {

    private String name;

    private String companyName;

    private Long empCode;

    private List<AddressResponse> addresses;

}
