package spw_reserved_hotel_db_auth_data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spw_reserved_hotel_db_auth_data.dto.type.UserType;

import java.util.List;
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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue
    private UUID id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String middleName;
    private String inn;
    private String ogrn;
    private String address;
    private String email;
    private String phone;
    private UserType userType;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoleDto> roles;
}
