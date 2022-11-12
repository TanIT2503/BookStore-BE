package com.bookstore.repository;

import com.bookstore.entity.book.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPromotionRepository extends JpaRepository<Promotion, Long> {
}
