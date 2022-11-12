package com.bookstore.entity.book;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    private String categoryName;
    private Boolean categoryFlag = false;
    @OneToMany(mappedBy = "bookCategoryId", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Book> bookList;

    public Category() {
    }

    public Category(Long categoryId, String categoryName, Boolean categoryFlag, List<Book> bookList) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryFlag = categoryFlag;
        this.bookList = bookList;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Boolean getCategoryFlag() {
        return categoryFlag;
    }

    public void setCategoryFlag(Boolean categoryFlag) {
        this.categoryFlag = categoryFlag;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}