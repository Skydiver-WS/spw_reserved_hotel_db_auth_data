package spw_reserved_hotel_db_auth_data.exception.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import spw_reserved_hotel_db_auth_data.exception.UsersException;
import spw_reserved_hotel_db_auth_data.web.response.UserResponse;

@ControllerAdvice
public class UsersExceptionHandler {
    @ExceptionHandler({UsersException.class})
    public ResponseEntity<UserResponse> exceptionsResponse(UsersException ex) {
        return ResponseEntity.ok(UserResponse.builder()
                .error(UserResponse.ErrorResponse.builder()
                        .message(ex.getMessage())
                        .build()).build());
    }
}
