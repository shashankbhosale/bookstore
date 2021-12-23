package com.shashank.app.services.bookstore.borrow;

import com.shashank.app.services.bookstore.book.Book;
import com.shashank.app.services.bookstore.patron.Patron;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
public class Borrow implements Serializable {

    @Id
    @GeneratedValue
    Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "book_id")
    Book book;

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "patron_id")
    Patron patron;

    private Date borrowedOn;

    public Borrow(){}


    public Borrow(Book book, Patron patron) {
        this(book, patron, new Date());
    }

    public Borrow(Book book, Patron patron, Date borrowedOn) {
        this.book = book;
        this.patron = patron;
        this.borrowedOn = borrowedOn;
    }
}
