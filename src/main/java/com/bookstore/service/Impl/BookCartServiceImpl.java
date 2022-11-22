package com.bookstore.service.Impl;

import com.bookstore.entity.cart.BookCart;
import com.bookstore.entity.cart.Cart;
import com.bookstore.repository.IBookCartRepository;
import com.bookstore.service.IBookCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookCartServiceImpl implements IBookCartService {
    @Autowired
    IBookCartRepository bookCartRepository;

    @Override
    public List<BookCart> findAllBookCart(Long id) {
        return bookCartRepository.findAllBookCart(id);
    }

    @Override
    public BookCart addBook(BookCart bookCart) {
        return bookCartRepository.save(bookCart);
    }

    @Override
    public BookCart findByCartId(Cart cart) {
        return bookCartRepository.findByCartId(cart);
    }
}
