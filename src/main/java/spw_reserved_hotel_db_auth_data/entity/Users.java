package spw_reserved_hotel_db_auth_data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Users {

    @Id
    @GeneratedValue
    private UUID id;
    private String username;
    private String password;
    private String inn;
    private String ogrn;
    private String address;

    @OneToMany
    private List<RoleDto> roleDto;
}
