package spw_reserved_hotel_db_auth_data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spw_reserved_hotel_db_auth_data.dto.type.RolesType;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Enumerated(EnumType.STRING)
    private RolesType rolesType;
}
