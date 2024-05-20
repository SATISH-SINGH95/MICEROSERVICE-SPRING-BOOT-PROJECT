package com.user.service;

import java.util.List;
import com.user.model.request.UserRequestObject;
import com.user.model.response.UserResponseObject;

public interface UserService {

    UserResponseObject createUser(UserRequestObject userRequestObject);

    List<UserResponseObject> getAllUser(Integer pageNumber, Integer pageSize, String sortingField, String sortingOrder);

    UserResponseObject getUserById(Long userId);

}
