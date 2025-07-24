package com.customeronboarding.service;

import com.customeronboarding.dto.UserRequestDTO;
import com.customeronboarding.dto.UserResponseDTO;
import com.customeronboarding.entity.User;
import com.customeronboarding.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Create
    public UserResponseDTO createUser(UserRequestDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPasswordHash(userDTO.getPasswordHash());
        user.setRole(userDTO.getRole());
        return toResponseDTO(userRepository.save(user));
    }

    // Read - Get by ID
    public Optional<UserResponseDTO> getUserById(Long id) {
        return userRepository.findById(id).map(this::toResponseDTO);
    }

    // Read - Get All
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Update
    public Optional<UserResponseDTO> updateUser(Long id, UserRequestDTO updatedDTO) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(updatedDTO.getUsername());
            user.setPasswordHash(updatedDTO.getPasswordHash());
            user.setRole(updatedDTO.getRole());
            return toResponseDTO(userRepository.save(user));
        });
    }

    // Delete
    public boolean deleteUser(Long id) {
        return userRepository.findById(id).map(user -> {
            userRepository.delete(user);
            return true;
        }).orElse(false);
    }

    // Find by Username
    public Optional<UserResponseDTO> findByUsername(String username) {
        return userRepository.findByUsername(username).map(this::toResponseDTO);
    }

    // Helper Method: Entity → DTO
    private UserResponseDTO toResponseDTO(User user) {
        return new UserResponseDTO(
                user.getUserId(),
                user.getUsername(),
                user.getRole(),
                user.getCreatedAt()
        );
    }
}
