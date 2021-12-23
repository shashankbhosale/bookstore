package com.shashank.app.services.bookstore.borrow;

import com.shashank.app.services.bookstore.book.Book;
import com.shashank.app.services.bookstore.book.BookRepository;
import com.shashank.app.services.bookstore.patron.Patron;
import com.shashank.app.services.bookstore.patron.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BorrowController {

    BorrowRepository borrowRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    PatronRepository patronRepository;


    BorrowController(BorrowRepository borrowRepository){
        this.borrowRepository = borrowRepository;
    }


    @PostMapping("/borrow/{patronId}/{bookId}")
    void borrowBook(@PathVariable Long patronId, @PathVariable Long bookId){
        Book b1 = bookRepository.getById(bookId);
        Patron p1 = patronRepository.getById(patronId);

        Borrow b = new Borrow(b1, p1);
        b1.addBorrow(b);
        bookRepository.save(b1);
    }

    @DeleteMapping("/return/{patronId}/{bookId}")
    void returnBook(@PathVariable Long patronId, @PathVariable Long bookId){
        Book b1 = bookRepository.getById(bookId);
        Borrow currentB = null;
        for(Borrow b : b1.getBorrows()){
            long id = b.getPatron().getId();
            if(id == patronId){
                currentB = b;
                break;
            }
        }
        if(currentB!= null){
            b1.getBorrows().remove(currentB);
        }
        bookRepository.save(b1);
    }


    @GetMapping("/borrowed/all/books/list")
    List<Book> allBorrowed(){
        return borrowRepository.getBorrowedList();
    }

    @GetMapping("/borrowed/all/books/size")
    Long allBorrowedCount(){
        return borrowRepository.getBorrowedCount();
    }

    @GetMapping("/patron/borrowed/{patronId}")
    List<Book> patronBorrowed(@PathVariable Long patronId){
        return borrowRepository.getBorrowedList();
    }

    @GetMapping("/patron/borrowed/{patronId}/size")
    Long patronBorrowedCount(@PathVariable Long patronId){
        return borrowRepository.getBorrowedCount();
    }


}
