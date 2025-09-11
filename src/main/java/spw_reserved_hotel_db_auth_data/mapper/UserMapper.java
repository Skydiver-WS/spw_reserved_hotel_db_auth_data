package spw_reserved_hotel_db_auth_data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import spw_reserved_hotel_db_auth_data.entity.RoleDto;
import spw_reserved_hotel_db_auth_data.entity.Users;
import spw_reserved_hotel_db_auth_data.web.request.UserRequest;
import spw_reserved_hotel_db_auth_data.web.response.UserResponse;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {


    @Mapping(target = "roles", source = "role")
    Users userFromUserRequest(UserRequest userRequest);

    @Mapping(target = "role", source = "roles")
    UserResponse userResponseFromUser(Users user);

    List<UserResponse> userResponsesFromUsers(List<Users> users);

    void userUpdateFromUserAndUserRequest(@MappingTarget Users user, UserRequest userRequest);

    @Named("mappingRole")
    default List<String> mappingRoles(List<RoleDto> role){
        return role.stream().map(r -> r.getRolesType().name()).toList();
    }
}
