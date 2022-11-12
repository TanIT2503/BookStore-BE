package com.bookstore.controller;

import com.bookstore.entity.book.Book;
import com.bookstore.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin
@RequestMapping("api/book")
public class BookController {
    @Autowired
    private IBookService bookService;

    @GetMapping()
    public ResponseEntity<List<Book>> getTopNewBook(){
        List<Book> bookList = bookService.findTopNewBook();
        if (bookList.isEmpty()) {
            return new ResponseEntity<List<Book>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Book>>(bookList, HttpStatus.OK);
    }
    @GetMapping("/latest")
    public ResponseEntity<Page<Book>> getAllTopNewBook(@PageableDefault(value = 8) Pageable pageable){
        Page<Book> books = bookService.findAllTopNewBook(pageable);
        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long id){
        try{
            Book book = bookService.findById(id);
            return new ResponseEntity<Book>(book, HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
        }
    }
}
