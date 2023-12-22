package com.example.blog.blogappapis.Payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private Integer categoryId;

    @NotEmpty
    @Size(min=4,message = "Category title must have minimum of 4 characters")
    private String categoryTitle;
    @NotEmpty
    @Size(max = 10,message = "Category Description must have maximum 10 characters")
    private String categoryDescription;
}
