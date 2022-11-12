package com.bookstore.repository;

import com.bookstore.entity.book.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookRepository extends JpaRepository<Book, Long> {
    @Query(value = "SELECT * FROM book order by book_publish_date desc", nativeQuery = true)
    Page<Book> findAllTopNewBook(Pageable pageable);

    @Query(value = "SELECT * FROM book order by book_publish_date desc limit 8", nativeQuery = true)
    List<Book> findTopNewBook();
}
