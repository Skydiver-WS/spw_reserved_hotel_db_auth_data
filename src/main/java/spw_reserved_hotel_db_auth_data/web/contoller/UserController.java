package spw_reserved_hotel_db_auth_data.web.contoller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spw_reserved_hotel_db_auth_data.service.UserService;
import spw_reserved_hotel_db_auth_data.web.request.UserRequest;
import spw_reserved_hotel_db_auth_data.web.response.UserResponse;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;


    @PostMapping("/create")
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest userRequest) {
        return ResponseEntity.ok(userService.createUser(userRequest));
    }

    @PostMapping("/sing-in")
    public ResponseEntity<UserResponse> singIn(@RequestBody @Valid UserRequest userRequest) {
        UserResponse response = userService.authenticate(userRequest);
        return ResponseEntity.ok(response);
    }


    @PutMapping
    public ResponseEntity<UserResponse> updateUser(@RequestBody @Valid UserRequest userRequest){
        return ResponseEntity.ok(userService.updateUser(userRequest));
    }

    @DeleteMapping
    public ResponseEntity<UserResponse> deleteUser(@RequestBody @Valid  UserRequest userRequest){
        return ResponseEntity.ok(userService.deleteUser(userRequest));
    }


    @GetMapping("/all-users")
    public ResponseEntity<UserResponse> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());

    }
}
