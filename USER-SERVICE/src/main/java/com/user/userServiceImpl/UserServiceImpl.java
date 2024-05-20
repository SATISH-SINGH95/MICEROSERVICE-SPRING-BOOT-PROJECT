package com.user.userServiceImpl;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.user.client.AddressClient;
import com.user.constants.UserConstants;
import com.user.exception.EntityNotFoundException;
import com.user.exception.ServiceUnavailableException;
import com.user.model.entity.User;
import com.user.model.request.UserRequestObject;
import com.user.model.response.AddressResponse;
import com.user.model.response.UserResponseObject;
import com.user.repository.UserRepository;
import com.user.service.UserService;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressClient addressClient;

    @Autowired
    private MessageSource messageSource;

    @Override
    public UserResponseObject createUser(UserRequestObject userRequestObject) {

        UserResponseObject responseObject = new UserResponseObject();

        User user = new User();
        user.setName(userRequestObject.getName());
        user.setCompanyName(userRequestObject.getCompanyName());
        user.setEmpCode(userRequestObject.getEmpCode());
        User savedUser = userRepository.save(user);
        if(savedUser != null){
            responseObject = savedUser.getAResponseObject();
        }
        return responseObject;
    }

    @Override
    public List<UserResponseObject> getAllUser(Integer pageNumber, Integer pageSize, String sortingField, String sortingOrder) {

        Sort sort = sortingOrder.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortingField).ascending() : Sort.by(sortingField).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<User> page = userRepository.findAll(pageable);
        List<User> userInfos = page.getContent();

        if(userInfos == null || userInfos.isEmpty()){
            throw new EntityNotFoundException(UserConstants.USER_NOT_FOUND, HttpStatus.NOT_FOUND);
        }

        List<UserResponseObject> userInfoResponseObjects = userInfos.stream().map(user -> user.getAResponseObject()).collect(Collectors.toList());

        return userInfoResponseObjects;

        // List<User> users = userRepository.findAll();
        // if(users.isEmpty()){
        //     throw new EntityNotFoundException(UserConstants.NO_USERS_FOUND, HttpStatus.NOT_FOUND);
        // }
        // List<UserResponseObject> userResponses = users.stream().map(u-> u.getAResponseObject()).collect(Collectors.toList());
        // return userResponses;
    }

    @Override
    public UserResponseObject getUserById(Long userId) {

        User user = userRepository.findUserByUserId(userId).orElseThrow(
            ()-> new EntityNotFoundException(UserConstants.USER_NOT_FOUND, HttpStatus.NOT_FOUND)
        );

        ResponseEntity<List<AddressResponse>> addressResponse = null;

        try {
            addressResponse = addressClient.getAddressByUserId(userId);
        } catch (Exception ex) {
            throw new ServiceUnavailableException("Address service is unavailable", HttpStatus.SERVICE_UNAVAILABLE);
        }


        if(addressResponse.getBody() == null){
            throw new ServiceUnavailableException(messageSource.getMessage(UserConstants.SERVICE_UNAVAILBLE, null, Locale.getDefault()), HttpStatus.NOT_FOUND);
        }
         UserResponseObject responseObject = user.getAResponseObject();
         responseObject.setAddresses(addressResponse.getBody());
         return responseObject;
    }

    // private Pageable getPageableObject(Integer pageNumber, Integer pageSize, SortingFieldEnum sortingFieldEnum,
    //         OrderByEnum sortingOrderEnum) {

    //     Pageable pageable = null;
    //     switch (sortingFieldEnum) {
    //         case NAME:
    //             if(sortingOrderEnum.getValue() == 0){
    //                 pageable = PageRequest.of(pageNumber, pageSize, Sort.by(
    //                     Sort.Order.asc(sortingFieldEnum.getValue())));
    //             } else{
    //                 pageable = PageRequest.of(pageNumber, pageSize, Sort.by(
    //                     Sort.Order.desc(sortingFieldEnum.getValue())));
    //             }
    //             break;

    //         case COMPANY_NAME:
    //             if(sortingOrderEnum.getValue() == 0){
    //                 pageable = PageRequest.of(pageNumber, pageSize, Sort.by(
    //                     Sort.Order.asc(sortingFieldEnum.getValue())));
    //             } else{
    //                 pageable = PageRequest.of(pageNumber, pageSize, Sort.by(
    //                     Sort.Order.desc(sortingFieldEnum.getValue())));
    //             }
    //             break;

    //         case EMPLOYEE_CODE:
    //             if(sortingOrderEnum.getValue() == 0){
    //                 pageable = PageRequest.of(pageNumber, pageSize, Sort.by(
    //                     Sort.Order.asc(sortingFieldEnum.getValue())));
    //             } else{
    //                 pageable = PageRequest.of(pageNumber, pageSize, Sort.by(
    //                     Sort.Order.desc(sortingFieldEnum.getValue())));
    //             }
    //             break;

    //         default:
    //             throw new BadRequestException(UserConstants.BAD_REQUEST, HttpStatus.BAD_REQUEST);
    //     }

    //     return pageable;
    // }

}
