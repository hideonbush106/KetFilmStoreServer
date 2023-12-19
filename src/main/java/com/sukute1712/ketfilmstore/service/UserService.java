package com.sukute1712.ketfilmstore.service;

import com.sukute1712.ketfilmstore.entity.User;
import com.sukute1712.ketfilmstore.exception.AppException;
import com.sukute1712.ketfilmstore.exception.ResourceNotFoundException;
import com.sukute1712.ketfilmstore.jwt.JwtAuthenticationFilter;
import com.sukute1712.ketfilmstore.jwt.JwtTokenProvider;
import com.sukute1712.ketfilmstore.payload.Message;
import com.sukute1712.ketfilmstore.payload.dto.auth.PasswordDto;
import com.sukute1712.ketfilmstore.payload.dto.user.UserListDto;
import com.sukute1712.ketfilmstore.payload.dto.user.UserProfileRequestDto;
import com.sukute1712.ketfilmstore.payload.dto.user.UserProfileResponseDto;
import com.sukute1712.ketfilmstore.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private JwtTokenProvider jwtTokenProvider;
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

    public UserProfileResponseDto getOwnUser(HttpServletRequest request) {
        User user = getAuthenticatedUser(request);
        return modelMapper.map(user, UserProfileResponseDto.class);
    }

    public List<UserListDto> getAll() {
        List<User> userList = userRepository.findAll();
        return userList.stream()
                .map(user -> modelMapper.map(user, UserListDto.class))
                .collect(Collectors.toList());
    }

    public User getAuthenticatedUser(HttpServletRequest request) {
        String token = jwtAuthenticationFilter.getTokenFromRequest(request);
        String username = jwtTokenProvider.getUsername(token);
        return userRepository.findByUsernameOrEmail(username, username)
                .orElseThrow(() -> new ResourceNotFoundException("User"));
    }

    public Message changePassword(PasswordDto password, HttpServletRequest request) {

        User user = getAuthenticatedUser(request);

        if (!passwordEncoder.matches(password.getOldPassword(), user.getPassword())) {
            throw new AppException(HttpStatus.FORBIDDEN, "Incorrect password");
        }
        String newEncodedPassword = passwordEncoder.encode(password.getNewPassword());
        user.setPassword(newEncodedPassword);
        userRepository.save(user);
        return new Message("Password changed successfully");
    }

    public UserProfileResponseDto update(UserProfileRequestDto userProfileRequestDto, HttpServletRequest request) {
        //TODO: Implement this
        return null;
    }
}
