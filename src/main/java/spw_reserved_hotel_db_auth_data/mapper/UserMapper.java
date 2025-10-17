package spw_reserved_hotel_db_auth_data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import spw_reserved_hotel_db_auth_data.entity.RoleDto;
import spw_reserved_hotel_db_auth_data.entity.Users;
import spw_reserved_hotel_db_auth_data.exception.UsersException;
import spw_reserved_hotel_db_auth_data.web.request.UserRequest;
import spw_reserved_hotel_db_auth_data.web.response.UserResponse;

import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // Маппинг для Manager
    @Mapping(target = "id", source = "userId")
    @Mapping(target = "roles", source = "role")
    @Mapping(target = "firstName", source = "admin.firstName")
    @Mapping(target = "lastName", source = "admin.lastName")
    @Mapping(target = "middleName", source = "admin.middleName")
    @Mapping(target = "inn", source = "admin.inn")
    @Mapping(target = "ogrn", source = "admin.ogrn")
    @Mapping(target = "address", source = "admin.address")
    @Mapping(target = "phone", source = "admin.phone")
    @Mapping(target = "email", source = "admin.email")
    Users fromAdminRequest(UserRequest userRequest);

    // Маппинг для Manager
    @Mapping(target = "id", source = "userId")
    @Mapping(target = "roles", source = "role")
    @Mapping(target = "firstName", source = "manager.firstName")
    @Mapping(target = "lastName", source = "manager.lastName")
    @Mapping(target = "middleName", source = "manager.middleName")
    @Mapping(target = "inn", source = "manager.inn")
    @Mapping(target = "ogrn", source = "manager.ogrn")
    @Mapping(target = "address", source = "manager.address")
    @Mapping(target = "phone", source = "manager.phone")
    @Mapping(target = "email", source = "manager.email")
    Users fromManagerRequest(UserRequest userRequest);

    // Маппинг для Employee
    @Mapping(target = "id", source = "userId")
    @Mapping(target = "roles", source = "role")
    @Mapping(target = "firstName", source = "employee.firstName")
    @Mapping(target = "lastName", source = "employee.lastName")
    @Mapping(target = "middleName", source = "employee.middleName")
    @Mapping(target = "phone", source = "employee.phone")
    @Mapping(target = "email", source = "employee.email")
    @Mapping(target = "inn", ignore = true)
    @Mapping(target = "ogrn", ignore = true)
    @Mapping(target = "address", ignore = true)
    Users fromEmployeeRequest(UserRequest userRequest);

    // Маппинг для Client
    @Mapping(target = "id", source = "userId")
    @Mapping(target = "roles", source = "role")
    @Mapping(target = "firstName", source = "client.firstName")
    @Mapping(target = "lastName", source = "client.lastName")
    @Mapping(target = "middleName", source = "client.middleName")
    @Mapping(target = "phone", source = "client.phone")
    @Mapping(target = "email", source = "client.email")
    @Mapping(target = "inn", ignore = true)
    @Mapping(target = "ogrn", ignore = true)
    @Mapping(target = "address", ignore = true)
    Users fromClientRequest(UserRequest userRequest);

    @Mapping(target = "role", source = "roles")
    @Mapping(target = "userId", source = "id")
    UserResponse userResponseFromUser(Users user);

    List<UserResponse> userResponsesFromUsers(List<Users> users);

    void userUpdateFromUserAndUserRequest(@MappingTarget Users user, UserRequest userRequest);

    @Named("mappingRole")
    default List<String> mappingRoles(List<RoleDto> role){
        return role.stream().map(r -> r.getRolesType().name()).toList();
    }

    default Users userFromUserRequest(UserRequest userRequest){
        if (Objects.nonNull(userRequest.getAdmin())){
            return fromAdminRequest(userRequest);
        }
        if (Objects.nonNull(userRequest.getManager())){
            return fromManagerRequest(userRequest);
        }
        if (Objects.nonNull(userRequest.getEmployee())){
            return fromEmployeeRequest(userRequest);
        }
        if (Objects.nonNull(userRequest.getClient())){
            return fromClientRequest(userRequest);
        }
        throw new UsersException("Objects admin, manager, employee or client from request are null");
    }
}
