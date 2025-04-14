package com.thinkingrodent.blog.blog_app_apis.payloads;

import com.thinkingrodent.blog.blog_app_apis.entities.Category;
import com.thinkingrodent.blog.blog_app_apis.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
    private Integer postId;
    private String title;
    private String content;
    private String imageName;
    private Date addedDate;
    private CategoryDto categoryDto;
    private UserDto userDto;

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public void setCategoryDto(CategoryDto categoryDto) {
        this.categoryDto = categoryDto;
    }
}
