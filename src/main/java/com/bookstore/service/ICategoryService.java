package com.bookstore.service;

import com.bookstore.entity.book.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> findAllListCategory();
    Category findById(Long id);
}
