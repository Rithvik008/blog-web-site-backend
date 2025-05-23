package com.thinkingrodent.blog.blog_app_apis.services.impl;

import com.thinkingrodent.blog.blog_app_apis.entities.Category;
import com.thinkingrodent.blog.blog_app_apis.exceptions.ResourceNotFoundException;
import com.thinkingrodent.blog.blog_app_apis.payloads.CategoryDto;
import com.thinkingrodent.blog.blog_app_apis.repositories.CategoryRepo;
import com.thinkingrodent.blog.blog_app_apis.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category=this.modelMapper.map(categoryDto,Category.class);
        Category savedCategory=this.categoryRepo.save(category);
        return this.modelMapper.map(savedCategory,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category=this.categoryRepo.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category","id",categoryId));
        category.setCategoryTitle(String.valueOf(categoryDto.getCategoryTitle()));
        category.setCategoryDescription(String.valueOf(categoryDto.getCategoryDescription()));

        Category updatedCategory=this.categoryRepo.save(category);
        return this.modelMapper.map(updatedCategory,CategoryDto.class);
    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        Category category=this.categoryRepo.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category","id",categoryId));
        CategoryDto categoryDto=this.modelMapper.map(category,CategoryDto.class);
        return categoryDto;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories=this.categoryRepo.findAll();
        List<CategoryDto> categoryDtos=categories.stream().map((category) -> this.modelMapper.map(category,CategoryDto.class)).collect(Collectors.toList());
        return categoryDtos;
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category=this.categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category","id",categoryId));
        this.categoryRepo.delete(category);
    }
}
