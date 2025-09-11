package spw_reserved_hotel_db_auth_data.dto.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RolesType {

    ROLE_ADMIN("администратор сервиса"),
    ROLE_MANAGER_HOTEL("владелец отеля"),
    ROLE_MANAGER_EMPLOYEE("управляющий отеля"),
    ROLE_CLIENT("клиент");

    private final String descriptionRole;
}
