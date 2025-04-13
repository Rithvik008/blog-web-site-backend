package com.thinkingrodent.blog.blog_app_apis.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private Integer categoryId;

    @NotBlank
    @Size(min=4,message = "Category name very short.")
    private String categoryTitle;

    @NotBlank
    @Size(min=30,message = "Description invalid.")
    private String categoryDescription;
}
