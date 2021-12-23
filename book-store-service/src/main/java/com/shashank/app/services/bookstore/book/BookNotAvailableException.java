package com.shashank.app.services.bookstore.book;

public class BookNotAvailableException extends RuntimeException{

    public BookNotAvailableException(Book book) {
        super("Book not in stock : " + book.getName());
    }
}
