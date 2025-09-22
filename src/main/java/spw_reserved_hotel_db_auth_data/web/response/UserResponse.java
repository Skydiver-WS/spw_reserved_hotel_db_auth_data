package spw_reserved_hotel_db_auth_data.web.response;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    private String userId;
    private String username;
    private String password;
    private List <RoleDto> role;
    private List<UserResponse> users;
    private String message;
    private ErrorResponse error;


    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ErrorResponse {
        private String message;
    }

}
