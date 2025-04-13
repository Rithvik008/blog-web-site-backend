package com.thinkingrodent.blog.blog_app_apis.services;

import com.thinkingrodent.blog.blog_app_apis.payloads.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user, Integer userId);
    UserDto getUserById(Integer userid);
    List<UserDto> getAllUsers();
    void deleteUser(Integer userId);
}
