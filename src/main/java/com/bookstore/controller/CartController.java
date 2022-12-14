package com.bookstore.controller;

import com.bookstore.entity.book.Book;
import com.bookstore.entity.cart.BookCart;
import com.bookstore.entity.cart.Cart;
import com.bookstore.service.IAccountService;
import com.bookstore.service.IBookCartService;
import com.bookstore.service.IBookService;
import com.bookstore.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/cart")
public class CartController {
    @Autowired
    ICartService cartService;

    @Autowired
    IBookCartService bookCartService;

    @Autowired
    IAccountService accountService;

    @Autowired
    IBookService bookService;

    @GetMapping("/list-cart-book")
    public ResponseEntity<List<BookCart>> findAllCartBook(@RequestParam("accountId") Long accountId) {
        List<BookCart> cartBookList = bookCartService.findAllBookCart(accountId);
        if (cartBookList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(cartBookList, HttpStatus.OK);
        }
    }

    @PutMapping("/update-quantity")
    public ResponseEntity<BookCart> updateQuantityCart(@RequestBody BookCart bookCart) {
        Double totalMoney = bookCart.getBookId().getBookPrice() * bookCart.getCartId().getCartQuantity()
                - bookCart.getBookId().getBookPrice() * bookCart.getCartId().getCartQuantity()
                * (bookCart.getBookId().getBookPromotionId().getPromotion_percent() / 100);
        bookCart.getCartId().setCartTotalMoney(totalMoney);
        cartService.updateQuantityCart(bookCart.getCartId().getCartQuantity(), bookCart.getCartId().getCartTotalMoney(), bookCart.getCartId().getCartId());
        return new ResponseEntity<>(bookCart, HttpStatus.OK);
    }

    @PostMapping("/add-book")
    public ResponseEntity<?> addBook(@RequestParam("accountId") Long accountId
            , @RequestBody Book book) {
        Book bookById = bookService.findById(book.getBookId());
        List<BookCart> bookCartList = bookCartService.findAllBookCart(accountId);
        // ki???m tra t???n t???i gi??? h??ng
        for (BookCart cartBook : bookCartList) {
            // update s??? l?????ng
            // book.getBookQuantity() l?? s??? l?????ng ????a v??o gi??? h??ng
            if (cartBook.getBookId().getBookId() == book.getBookId()) {
                if ((book.getBookQuantity() + cartBook.getCartId().getCartQuantity()) > bookById.getBookQuantity()) {
                    String message = "S??? l?????ng s??ch th??m ???? l???n h??n s??? l?????ng trong kho hi???n t???i ho???c h???t h??ng. Vui l??ng nh???p l???i";
                    return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
                }
                cartBook.getCartId().setCartQuantity(cartBook.getCartId().getCartQuantity() + book.getBookQuantity());
                Double totalMoney = cartBook.getBookId().getBookPrice() * cartBook.getCartId().getCartQuantity()
                        - cartBook.getBookId().getBookPrice() * cartBook.getCartId().getCartQuantity()
                        * (cartBook.getBookId().getBookPromotionId().getPromotion_percent() / 100);
                cartBook.getCartId().setCartTotalMoney(totalMoney);
                cartService.updateQuantityCart(cartBook.getCartId().getCartQuantity(), cartBook.getCartId().getCartTotalMoney(), cartBook.getCartId().getCartId());
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        // th??m s??ch m???i v??o gi??? h??ng
        // book.getBookQuantity() l?? s??? l?????ng ????a v??o gi??? h??ng
        if (book.getBookQuantity() > bookById.getBookQuantity()) {
            String message = "S??? l?????ng s??ch th??m ???? l???n h??n s??? l?????ng trong kho hi???n t???i. Vui l??ng nh???p l???i";
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        Cart cart = new Cart();
        Double totalMoney = book.getBookPrice() * book.getBookQuantity()
                - book.getBookPrice() * book.getBookQuantity()
                * (book.getBookPromotionId().getPromotion_percent() / 100);
        cart.setCartQuantity(book.getBookQuantity());
        cart.setCartTotalMoney(totalMoney);
        cart.setCartAccountId(accountService.findById(accountId));

        Cart cartCreate = cartService.addBook(cart);

        BookCart cartBook = new BookCart();
        cartBook.setBookId(bookService.findById(book.getBookId()));
        cartBook.setCartId(cartCreate);

        BookCart cartBookCreate = bookCartService.addBook(cartBook);
        return new ResponseEntity<>(cartBookCreate, HttpStatus.OK);
    }

    @DeleteMapping("/cart-delete")
    public ResponseEntity<Cart> deleteCustomer(@RequestParam("cardId") Long cardId) {
        Optional<Cart> foundCart = cartService.findById(cardId);
        if (!foundCart.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            cartService.deleteCartById(cardId);
            return new ResponseEntity<>(foundCart.get(), HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/payment")
    public ResponseEntity<?> paymentCart(@RequestBody List<Cart> cartListPayment) {
        List<String> cartCodeList = cartService.checkCodeCart();

        // l???y m?? ho?? ????n
        String cartCode = "";
        for (String code : cartCodeList) {
            cartCode = code;
        }
        if (cartCode.equals("")) {
            cartCode = "1";
        }

        String cartCodePayment = "";
        String[] cartCodeArray = cartCode.split("-");
        System.out.println(Integer.parseInt(cartCodeArray[1]));
        if ((Integer.parseInt(cartCodeArray[1]) + 1) < 10) {
            cartCodePayment = "MHD-00" + (Integer.parseInt(cartCodeArray[1]) + 1);
        } else if (Integer.parseInt(cartCodeArray[1] + 1) < 100) {
            cartCodePayment = "MHD-0" + (Integer.parseInt(cartCodeArray[1]) + 1);
        } else {
            cartCodePayment = "MHD-" + (Integer.parseInt(cartCodeArray[1]) + 1);
        }

        // l???y ng??y hi???n t???i
        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formatted = current.format(formatter);

        BookCart bookCart = null;
        // ti???n h??nh thanh to??n
        for (Cart cart : cartListPayment) {
            cartService.paymentCart(cartCodePayment, formatted, true, cart.getCartId());

            // c???p nh???t l???i s??? l?????ng s??ch
            bookCart = bookCartService.findByCartId(cart);
            bookCart.getBookId().setBookQuantity(bookCart.getBookId().getBookQuantity() - cart.getCartQuantity());
            bookService.updateQuantityBook(bookCart.getBookId());
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
