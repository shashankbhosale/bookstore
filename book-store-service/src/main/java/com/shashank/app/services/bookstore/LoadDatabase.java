package com.shashank.app.services.bookstore;

import com.shashank.app.services.bookstore.book.Book;
import com.shashank.app.services.bookstore.book.BookRepository;
import com.shashank.app.services.bookstore.borrow.Borrow;
import com.shashank.app.services.bookstore.borrow.BorrowRepository;
import com.shashank.app.services.bookstore.patron.Patron;
import com.shashank.app.services.bookstore.patron.PatronRepository;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Date;

//@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner borrow(BookRepository bookRepository, PatronRepository patronRepository, BorrowRepository borrowRepository) {
        return args -> {
            Book book1 = new Book("My book 1", "Author1", 4, 39.00);
            Book book2 = new Book("My Book 2", "Author1", 1, 1.99);
            book1 = bookRepository.saveAndFlush(book1);
            book2 = bookRepository.saveAndFlush(book2);

            Patron patron1 = new Patron("My Name 1", parseDate("15/08/2000"), "091930123");
            Patron patron2 = new Patron("My Name 2", parseDate("31/03/2000"), "091-9300-0123");
            patron1 = patronRepository.saveAndFlush(patron1);
            patron2 = patronRepository.saveAndFlush(patron2);


            Borrow b1 = new Borrow(book1, patron1);
            book1.addBorrow(b1);
            bookRepository.saveAndFlush(book1);
        };
    }

    @SneakyThrows
    public Date parseDate(String dateStr){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = formatter.parse(dateStr);
        return date;
    }

}
