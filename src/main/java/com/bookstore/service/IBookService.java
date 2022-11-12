package com.bookstore.service;

import com.bookstore.entity.book.Book;
import org.springframework.data.domain.*;

import java.util.List;

public interface IBookService {
    Page<Book> findAllTopNewBook(Pageable pageable);
    List<Book> findTopNewBook();

    Book findById(Long id);
}
