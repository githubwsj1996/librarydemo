package com.sj.entity;

public class Book {
    private Integer id;
    private String name;
    private String author;
    private String publish;
    private Integer pages;
    private Float  price;
    private Integer bookCaseId;
    private String bookCaseName;

    public Integer getBookCaseId() {
        return bookCaseId;
    }

    public void setBookCaseId(Integer bookCaseId) {
        this.bookCaseId = bookCaseId;
    }

    public String getBookCaseName() {
        return bookCaseName;
    }

    public void setBookCaseName(String bookCaseName) {
        this.bookCaseName = bookCaseName;
    }

    public Book() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }



    public Book(String name, String author, String publish, Integer pages, Float price, Integer bookCaseId) {
        this.name = name;
        this.author = author;
        this.publish = publish;
        this.pages = pages;
        this.price = price;
        this.bookCaseId = bookCaseId;
    }

    public Book(Integer id, String name, String author, String publish, Integer pages, Float price, Integer bookCaseId) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.publish = publish;
        this.pages = pages;
        this.price = price;
        this.bookCaseId = bookCaseId;
    }

    public Book(String name, String author, String publish, Integer pages, Float price, String bookCaseName) {
        this.name = name;
        this.author = author;
        this.publish = publish;
        this.pages = pages;
        this.price = price;
        this.bookCaseName = bookCaseName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", publish='" + publish + '\'' +
                ", pages=" + pages +
                ", price=" + price +
                ", bookCaseId=" + bookCaseId +
                ", bookCaseName='" + bookCaseName + '\'' +
                '}';
    }
}
