package spw_reserved_hotel_db_auth_data.service;

import spw_reserved_hotel_db_auth_data.web.request.UserRequest;
import spw_reserved_hotel_db_auth_data.web.response.UserResponse;

import java.util.List;

public interface UserService {
    void createUser(UserRequest userRequest);

    UserResponse authenticate(UserRequest userRequest);

    boolean updateUser(UserRequest userRequest);

    boolean deleteUser(UserRequest userRequest);

    List<UserResponse> getAllUsers();
}
