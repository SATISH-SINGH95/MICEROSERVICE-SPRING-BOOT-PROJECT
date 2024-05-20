package com.address.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.address.constants.AddressConstants;
import com.address.exception.BadRequestException;
import com.address.exception.EntityNotFoundException;
import com.address.model.entity.AddressEntity;
import com.address.model.request.AddressRequest;
import com.address.model.response.AddressResponse;
import com.address.repository.AddressRepository;
import com.address.service.AddressService;


@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public AddressResponse saveAddress(AddressRequest addressRequest) {
        AddressEntity entity = new AddressEntity();
        entity.setUserId(addressRequest.getUserId());
        entity.setAddreessType(addressRequest.getAddreessType());
        entity.setAddress(addressRequest.getAddress());
        AddressEntity savedEntity = addressRepository.save(entity);
        if(savedEntity == null){
            throw new BadRequestException(AddressConstants.INVALID_REQUEST, HttpStatus.BAD_REQUEST);
        }
        AddressResponse response = savedEntity.getAsResponse();
        return response;

    }

    @Override
    public AddressResponse getAddressbyAddressId(Long addressId) {
        AddressEntity address = addressRepository.findAddressByAddressId(addressId).orElseThrow(
            () -> new EntityNotFoundException(AddressConstants.ADDRESS_NOT_FOUND, HttpStatus.NOT_FOUND));
        AddressResponse response = address.getAsResponse();
        return response;
    }

    @Override
    public List<AddressResponse> getAddressesByUserId(Long userId) {

        List<AddressEntity> addressList = addressRepository.findAddressesByUserId(userId);

        if(addressList.isEmpty() || addressList == null ){
            throw new EntityNotFoundException(AddressConstants.NO_ADDRESS_FOUND, HttpStatus.NOT_FOUND);
        }

        List<AddressResponse> responses = addressList.stream().map(add -> add.getAsResponse()).collect(Collectors.toList());
        return responses;
    }

}
