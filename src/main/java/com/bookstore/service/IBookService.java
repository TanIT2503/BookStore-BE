package com.bookstore.service;

import com.bookstore.entity.book.Book;
import org.springframework.data.domain.*;

import java.util.List;

public interface IBookService {
    Page<Book> findAllTopNewBook(Pageable pageable);
    Page<Book> findAllBookByCategoryId(Long categoryId, Pageable pageable);
    List<Book> findTopNewBook();

    Book findById(Long id);
    Page<Book> findAllBookByPromotion(Pageable pageable);

    Page<Book> searchBook(String searchKey, Pageable pageable);
}
