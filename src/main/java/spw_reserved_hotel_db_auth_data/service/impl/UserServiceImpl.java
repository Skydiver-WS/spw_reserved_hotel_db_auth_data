package spw_reserved_hotel_db_auth_data.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;
import spw_reserved_hotel_db_auth_data.entity.Users;
import spw_reserved_hotel_db_auth_data.exception.UsersException;
import spw_reserved_hotel_db_auth_data.mapper.UserMapper;
import spw_reserved_hotel_db_auth_data.repository.UserRepository;
import spw_reserved_hotel_db_auth_data.service.UserService;
import spw_reserved_hotel_db_auth_data.web.request.UserRequest;
import spw_reserved_hotel_db_auth_data.web.response.UserResponse;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        log.info("Creating user {}", userRequest.getUsername());
        Users user = userMapper.userFromUserRequest(userRequest);
        try {
            userRepository.saveAndFlush(user);
            return UserResponse.builder()
                    .message("User created")
                    .build();
        } catch (InvalidDataAccessApiUsageException ex) {
            log.error(ex.getMessage());
            ex.printStackTrace();
            throw new UsersException("User " + userRequest.getUsername() + " created failed");
        } catch (DataIntegrityViolationException ex) {
            log.warn(ex.getMessage());
            throw new UsersException("Username, inn, ogrn, email already exists");
        }
    }

    @Override
    public UserResponse authenticate(UserRequest userRequest) {
        log.info("Authenticating user {}", userRequest.getUsername());
        Optional<Users> usersOptional = userRepository.findByUsername(userRequest.getUsername());
        if (usersOptional.isEmpty()) {
            throw new UsersException("User " + userRequest.getUsername() + " not found in database");
        }
        Users user = usersOptional.get();
        return userMapper.userResponseFromUser(user);
    }

    @Override
    public UserResponse updateUser(UserRequest userRequest) {
        log.info("Updating user {}", userRequest.getUsername());
        Optional<Users> usersOptional = userRepository.findByUsername(userRequest.getUser());
        if (usersOptional.isEmpty()) {
            throw new UsersException("User " + userRequest.getUsername() + " not found in database");
        }
        Users user = usersOptional.get();
        userMapper.userUpdateFromUserAndUserRequest(user, userRequest);
        try {
            userRepository.saveAndFlush(user);

        } catch (DataIntegrityViolationException ex) {
            log.warn(ex.getMessage());
            throw new UsersException("Username, inn, ogrn, email already exists");
        }
        return UserResponse.builder()
                .message("User updated")
                .build();

    }

    @Override
    @Transactional
    public UserResponse deleteUser(UserRequest userRequest) {
        Optional<Users> usersOptional = userRepository.findByUsername(userRequest.getUsername());
        if (usersOptional.isEmpty()) {
            throw new UsersException("User " + userRequest.getUsername() + " not found in database");
        }
        Users user = usersOptional.get();
        userRepository.deleteById(user.getId());
        userRepository.flush();
        Optional<Users> deletedUser = userRepository.findByUsername(userRequest.getUsername());
        if (deletedUser.isPresent()) {
            log.warn("{} not found in database", userRequest.getUsername());
            throw new UsersException("User " + userRequest.getUsername() + " not remove from database");
        }
        return UserResponse.builder()
                .message("User delete")
                .build();
    }

    @Override
    public UserResponse getAllUsers() {
        return UserResponse.builder()
                .users(userMapper.userResponsesFromUsers(userRepository.findAll()))
                .build();
    }
}
