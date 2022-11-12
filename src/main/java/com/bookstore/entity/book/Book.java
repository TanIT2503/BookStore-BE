package com.bookstore.entity.book;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    private String bookName;
    private String bookCode;
    private String bookImage;
    private String bookContent;
    private double bookPrice;
    private String bookTranslator;
    private int bookTotalPage;
    private String bookSize;
    @Column(columnDefinition = "DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate bookPublishDate;
    private int bookQuantity;
    private String bookPublisher;
    private Boolean bookFlag = false;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_category_id")
    private Category bookCategoryId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_author_id")
    private Author bookAuthorId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_promotion_id")
    private Promotion bookPromotionId;

    public Book() {
    }

    public Book(Long bookId, String bookName, String bookCode, String bookImage, String bookContent, double bookPrice, String bookTranslator, int bookTotalPage, String bookSize, LocalDate bookPublishDate, int bookQuantity, String bookPublisher, Boolean bookFlag, Category bookCategoryId, Author bookAuthorId, Promotion bookPromotionId) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookCode = bookCode;
        this.bookImage = bookImage;
        this.bookContent = bookContent;
        this.bookPrice = bookPrice;
        this.bookTranslator = bookTranslator;
        this.bookTotalPage = bookTotalPage;
        this.bookSize = bookSize;
        this.bookPublishDate = bookPublishDate;
        this.bookQuantity = bookQuantity;
        this.bookPublisher = bookPublisher;
        this.bookFlag = bookFlag;
        this.bookCategoryId = bookCategoryId;
        this.bookAuthorId = bookAuthorId;
        this.bookPromotionId = bookPromotionId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    public String getBookImage() {
        return bookImage;
    }

    public void setBookImage(String bookImage) {
        this.bookImage = bookImage;
    }

    public String getBookContent() {
        return bookContent;
    }

    public void setBookContent(String bookContent) {
        this.bookContent = bookContent;
    }

    public double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getBookTranslator() {
        return bookTranslator;
    }

    public void setBookTranslator(String bookTranslator) {
        this.bookTranslator = bookTranslator;
    }

    public int getBookTotalPage() {
        return bookTotalPage;
    }

    public void setBookTotalPage(int bookTotalPage) {
        this.bookTotalPage = bookTotalPage;
    }

    public String getBookSize() {
        return bookSize;
    }

    public void setBookSize(String bookSize) {
        this.bookSize = bookSize;
    }

    public LocalDate getBookPublishDate() {
        return bookPublishDate;
    }

    public void setBookPublishDate(LocalDate bookPublishDate) {
        this.bookPublishDate = bookPublishDate;
    }

    public int getBookQuantity() {
        return bookQuantity;
    }

    public void setBookQuantity(int bookQuantity) {
        this.bookQuantity = bookQuantity;
    }

    public String getBookPublisher() {
        return bookPublisher;
    }

    public void setBookPublisher(String bookPublisher) {
        this.bookPublisher = bookPublisher;
    }

    public Boolean getBookFlag() {
        return bookFlag;
    }

    public void setBookFlag(Boolean bookFlag) {
        this.bookFlag = bookFlag;
    }

    public Category getBookCategoryId() {
        return bookCategoryId;
    }

    public void setBookCategoryId(Category bookCategoryId) {
        this.bookCategoryId = bookCategoryId;
    }

    public Author getBookAuthorId() {
        return bookAuthorId;
    }

    public void setBookAuthorId(Author bookAuthorId) {
        this.bookAuthorId = bookAuthorId;
    }

    public Promotion getBookPromotionId() {
        return bookPromotionId;
    }

    public void setBookPromotionId(Promotion bookPromotionId) {
        this.bookPromotionId = bookPromotionId;
    }
}