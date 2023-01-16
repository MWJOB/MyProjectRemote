package com.deeplify.tutorial.oauthlogin.api.mapper;


import com.deeplify.tutorial.oauthlogin.api.dto.UserDto;
import com.deeplify.tutorial.oauthlogin.api.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Service;

@Service
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    User userPostToUser(UserDto.post requestBody);
    UserDto.Response userToUserResponse(User user);
}
