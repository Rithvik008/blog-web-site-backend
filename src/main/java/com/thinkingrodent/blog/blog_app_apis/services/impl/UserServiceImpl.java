package com.thinkingrodent.blog.blog_app_apis.services.impl;

import com.thinkingrodent.blog.blog_app_apis.entities.User;
import com.thinkingrodent.blog.blog_app_apis.payloads.UserDto;
import com.thinkingrodent.blog.blog_app_apis.repositories.UserRepo;
import com.thinkingrodent.blog.blog_app_apis.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.thinkingrodent.blog.blog_app_apis.exceptions.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user=this.dtoToUser(userDto);
        User savedUser=this.userRepo.save(user);

        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user=this.userRepo.findById(userId)
                .orElseThrow((() -> new ResourceNotFoundException("User"," id ",userId)));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());
        user.setPassword(userDto.getPassword());


        User updatedUser=this.userRepo.save(user);
        UserDto userDto1=this.userToDto(updatedUser);
        return userDto1;
    }

    @Override
    public UserDto getUserById(Integer userId) {

        User user=this.userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
        UserDto userDto=this.userToDto(user);
        return userDto;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users=this.userRepo.findAll();
        List<UserDto> userDtos=users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user=this.userRepo.findById(userId).
                orElseThrow(()->new ResourceNotFoundException("User","id",userId));
        this.userRepo.delete(user);
    }

    public User dtoToUser(UserDto userDto){
        User user= this.modelMapper.map(userDto,User.class);
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());
        return user;
    }

    public UserDto userToDto(User user){
        UserDto userDto= this.modelMapper.map(user,UserDto.class);
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());
        return userDto;

    }
}
