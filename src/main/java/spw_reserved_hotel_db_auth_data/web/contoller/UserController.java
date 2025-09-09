package spw_reserved_hotel_db_auth_data.web.contoller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spw_reserved_hotel_db_auth_data.service.UserService;
import spw_reserved_hotel_db_auth_data.web.request.UserRequest;
import spw_reserved_hotel_db_auth_data.web.response.UserResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;


    @PostMapping("/create")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        userService.createUser(userRequest);
        return ResponseEntity.ok(UserResponse.builder()
                .build());
    }

    @PostMapping("/sing-in")
    public ResponseEntity<UserResponse> singIn(@RequestBody UserRequest userRequest) {
        UserResponse response = userService.authenticate(userRequest);
        return ResponseEntity.ok(response);
    }


    @PutMapping
    public ResponseEntity<Void> updateUser(@RequestBody UserRequest userRequest){
        boolean update = userService.updateUser(userRequest);
        return update ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@RequestBody UserRequest userRequest){
        boolean update = userService.deleteUser(userRequest);
        return update ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
    }


    @GetMapping("/all-users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());

    }
}
