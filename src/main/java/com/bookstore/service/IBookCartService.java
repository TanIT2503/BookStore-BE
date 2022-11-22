package com.bookstore.service;

import com.bookstore.entity.cart.BookCart;
import com.bookstore.entity.cart.Cart;

import java.util.List;

public interface IBookCartService {
    List<BookCart> findAllBookCart(Long id);

    BookCart addBook(BookCart bookCart);

    BookCart findByCartId(Cart cart);
}
