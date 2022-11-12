package com.bookstore.entity.book;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "promotion")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long promotionId;
    private double promotion_percent;


    @OneToMany(mappedBy = "bookPromotionId", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Book> bookList;

    public Promotion() {
    }

    public Promotion(Long promotionId, double promotion_percent, List<Book> bookList) {
        this.promotionId = promotionId;
        this.promotion_percent = promotion_percent;
        this.bookList = bookList;
    }

    public Long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Long promotionId) {
        this.promotionId = promotionId;
    }

    public double getPromotion_percent() {
        return promotion_percent;
    }

    public void setPromotion_percent(double promotion_percent) {
        this.promotion_percent = promotion_percent;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
