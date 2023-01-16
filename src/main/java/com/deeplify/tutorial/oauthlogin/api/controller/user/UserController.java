package com.deeplify.tutorial.oauthlogin.api.controller.user;

import com.deeplify.tutorial.oauthlogin.api.dto.UserDto;
import com.deeplify.tutorial.oauthlogin.api.entity.user.User;
import com.deeplify.tutorial.oauthlogin.api.mapper.UserMapper;
import com.deeplify.tutorial.oauthlogin.api.service.UserService;
import com.deeplify.tutorial.oauthlogin.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper mapper;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<UserDto.Response> postMember(@Valid @RequestBody UserDto.post requestBody){
        User createUser = userService.createUser(requestBody);

        return ApiResponse.success("user", mapper.userToUserResponse(createUser));
    }

    @GetMapping
    public ApiResponse getUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userService.getUser(principal.getUsername());

        return ApiResponse.success("user", user);
    }
}
