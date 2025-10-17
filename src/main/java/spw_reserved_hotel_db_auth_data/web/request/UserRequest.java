package spw_reserved_hotel_db_auth_data.web.request;


import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spw_reserved_hotel_db_auth_data.entity.RoleDto;

import java.util.List;
import java.util.UUID;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest  {
    private UUID userId;
    private String user;
    @NotNull
    private String username;
    @NotNull
    private String password;
    private List<RoleDto> role;
    private AdminRequest admin;
    private ManagerRequest manager;
    private EmployeeRequest employee;
    private ClientRequest client;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class AdminRequest {
        private String firstName;
        private String lastName;
        private String middleName;
        private String inn;
        private String ogrn;
        private String address;
        private String phone;
        private String email;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ManagerRequest {

        private String firstName;
        private String lastName;
        private String middleName;
        private String inn;
        private String ogrn;
        private String address;
        private String phone;
        private String email;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EmployeeRequest {
        private String firstName;
        private String lastName;
        private String middleName;
        private String phone;
        private String email;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ClientRequest {

        private String firstName;
        private String lastName;
        private String middleName;
        private String phone;
        private String email;
    }
}
