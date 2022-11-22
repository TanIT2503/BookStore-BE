package com.bookstore.repository;

import com.bookstore.entity.cart.BookCart;
import com.bookstore.entity.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface IBookCartRepository extends JpaRepository<BookCart, Long> {
    @Query(value = "SELECT `cart_book`.*, `cart`.cart_status, `cart`.cart_account_id FROM `cart_book`\n" +
            "join `cart` on `cart` .cart_id = `cart_book`.cart_id\n" +
            "having `cart_book`.book_id > 0 and `cart`.cart_status = 0 and `cart`.cart_account_id = ?1\n" +
            ";", nativeQuery = true)
    List<BookCart> findAllBookCart(Long id);

    BookCart findByCartId(Cart cart);
}
