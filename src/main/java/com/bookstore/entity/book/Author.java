package com.bookstore.entity.book;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorId;
    private String authorName;

    private Boolean authorFlag = false;

    @OneToMany(mappedBy = "bookAuthorId", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Book> bookList;

    public Author() {
    }

    public Author(Long authorId, String authorName, Boolean authorFlag, List<Book> bookList) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.authorFlag = authorFlag;
        this.bookList = bookList;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Boolean getAuthorFlag() {
        return authorFlag;
    }

    public void setAuthorFlag(Boolean authorFlag) {
        this.authorFlag = authorFlag;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
