package com.thinkingrodent.blog.blog_app_apis.payloads;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private int id;

    @NotBlank
    @Size(min=4,message="Username must be min of 4 characters.")
    private String name;

    @NotBlank
    @Size(min=3,max=12,message="Password must be mre than 3 and less than 12 characters.")
    private String password;

    @NotBlank
    private String about;

    @Email(message="Email not valid.")
    private String email;
}
