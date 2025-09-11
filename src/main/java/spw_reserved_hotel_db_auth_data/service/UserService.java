package spw_reserved_hotel_db_auth_data.service;

import spw_reserved_hotel_db_auth_data.web.request.UserRequest;
import spw_reserved_hotel_db_auth_data.web.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest userRequest);

    UserResponse authenticate(UserRequest userRequest);

    UserResponse updateUser(UserRequest userRequest);

    UserResponse deleteUser(UserRequest userRequest);

    UserResponse getAllUsers();
}
