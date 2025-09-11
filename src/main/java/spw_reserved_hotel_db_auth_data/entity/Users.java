package spw_reserved_hotel_db_auth_data.entity;

import jakarta.persistence.*;
import lombok.Data;
import spw_reserved_hotel_db_auth_data.dto.type.UserType;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Data
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "inn"),
        @UniqueConstraint(columnNames = "ogrn"),
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "phone")
})
public class Users {

    @Id
    @GeneratedValue
    private UUID id;
    private String username;
    private String password;
    private String inn;
    private String ogrn;
    private String address;
    private String email;
    private String phone;
    private UserType userType = selectedUserType();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoleDto> roles;

    private UserType selectedUserType() {
        if (Objects.nonNull(inn) && Objects.nonNull(ogrn) && Objects.nonNull(address)) {
            return UserType.ORGANIZATION;
        }
        return UserType.PHYSICAL;
    }
}
