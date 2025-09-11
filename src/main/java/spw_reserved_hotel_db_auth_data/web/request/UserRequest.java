package spw_reserved_hotel_db_auth_data.web.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spw_reserved_hotel_db_auth_data.entity.RoleDto;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest  {
    private String user;
    private String username;
    private String password;
    private String inn;
    private String ogrn;
    private String address;
    private String email;
    private String phone;
    private List<RoleDto> role;
}
