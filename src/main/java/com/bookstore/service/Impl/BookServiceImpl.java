package com.bookstore.service.Impl;

import com.bookstore.entity.book.Book;
import com.bookstore.repository.IBookRepository;
import com.bookstore.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements IBookService {
    @Autowired
    private IBookRepository bookRepository;
    @Override
    public Page<Book> findAllTopNewBook(Pageable pageable) {
        return bookRepository.findAllTopNewBook(pageable);
    }

    @Override
    public List<Book> findTopNewBook() {
        return bookRepository.findTopNewBook();
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }
}
