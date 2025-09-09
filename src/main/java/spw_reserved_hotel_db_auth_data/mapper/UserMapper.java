package spw_reserved_hotel_db_auth_data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import spw_reserved_hotel_db_auth_data.entity.Users;
import spw_reserved_hotel_db_auth_data.web.request.UserRequest;
import spw_reserved_hotel_db_auth_data.web.response.UserResponse;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    Users userFromUserRequest(UserRequest userRequest);
    UserResponse userResponseFromUser(Users user);
    List<UserResponse> userResponsesFromUsers(List<Users> users);
    void userUpdateFromUserAndUserRequest(@MappingTarget Users user, UserRequest userRequest);
}
