package spw_reserved_hotel_db_auth_data.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spw_reserved_hotel_db_auth_data.entity.Users;
import spw_reserved_hotel_db_auth_data.exception.UsersNotFoundException;
import spw_reserved_hotel_db_auth_data.mapper.UserMapper;
import spw_reserved_hotel_db_auth_data.repository.UserRepository;
import spw_reserved_hotel_db_auth_data.service.UserService;
import spw_reserved_hotel_db_auth_data.web.request.UserRequest;
import spw_reserved_hotel_db_auth_data.web.response.UserResponse;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public void createUser(UserRequest userRequest) {
        log.info("Creating user {}", userRequest.getUsername());
        Users user = userMapper.userFromUserRequest(userRequest);
        userRepository.saveAndFlush(user);

    }

    @Override
    public UserResponse authenticate(UserRequest userRequest) {
        log.info("Authenticating user {}", userRequest.getUsername());
        Users user = userRepository.findByUsernameAndPassword(userRequest.getUsername(), userRequest.getPassword())
                .orElseThrow(() -> new UsersNotFoundException("Users " + userRequest.getUsername()
                        + " not found in database"));
        return userMapper.userResponseFromUser(user);
    }

    @Override
    public boolean updateUser(UserRequest userRequest) {
        log.info("Updating user {}", userRequest.getUsername());
        Optional <Users> usersOptional = userRepository.findByUsername(userRequest.getUsername());
        if (usersOptional.isEmpty()) {
            return false;
        }
        Users user = usersOptional.get();
        userMapper.userUpdateFromUserAndUserRequest(user, userRequest);
        userRepository.saveAndFlush(user);
        return true;
    }

    @Override
    public boolean deleteUser(UserRequest userRequest) {
        Optional <Users> usersOptional = userRepository.findByUsername(userRequest.getUsername());
        if (usersOptional.isEmpty()) {
            return false;
        }
        return false;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userMapper.userResponsesFromUsers(userRepository.findAll());
    }
}
