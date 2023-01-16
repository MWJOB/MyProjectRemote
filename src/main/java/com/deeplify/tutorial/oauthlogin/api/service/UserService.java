package com.deeplify.tutorial.oauthlogin.api.service;

import com.deeplify.tutorial.oauthlogin.api.dto.UserDto;
import com.deeplify.tutorial.oauthlogin.api.entity.user.User;
import com.deeplify.tutorial.oauthlogin.api.repository.user.UserRepository;
import com.deeplify.tutorial.oauthlogin.oauth.entity.ProviderType;
import com.deeplify.tutorial.oauthlogin.oauth.entity.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final String NoEmail = "NO Email";

    public User getUser(String userId) {
        return userRepository.findByUserId(userId);
    }
    public User createUser(UserDto.post req) {
        User user = createSignupFormOfUser(req);
        userRepository.save(user);

        return userRepository.saveAndFlush(user);
    }

    private User createSignupFormOfUser(UserDto.post req) {
        LocalDateTime now = LocalDateTime.now();
        User user = User.builder()
                .userId(req.getUserId())
                .username(req.getUsername())
                .email(NoEmail)
                .password(passwordEncoder.encode(req.getPassword()))
                .providerType(ProviderType.LOCAL)
                .profileImageUrl("https://user-images.githubusercontent.com/95069395/211246989-dd36a342-bf18-412e-b3ec-841ab3280d56.png")
                .roleType(RoleType.USER)
                .createdAt(now)
                .modifiedAt(now)
                .build();
        return user;
    }
}
