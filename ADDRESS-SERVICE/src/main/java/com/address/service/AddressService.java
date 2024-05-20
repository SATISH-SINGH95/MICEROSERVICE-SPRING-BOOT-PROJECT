package com.address.service;

import java.util.List;

import com.address.model.request.AddressRequest;
import com.address.model.response.AddressResponse;

public interface AddressService {

    AddressResponse saveAddress(AddressRequest addressRequest);

    AddressResponse getAddressbyAddressId(Long addressId);

    List<AddressResponse> getAddressesByUserId(Long userId);

}