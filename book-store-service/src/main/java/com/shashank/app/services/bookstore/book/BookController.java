package com.shashank.app.services.bookstore.book;

import com.shashank.app.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class BookController {
    private final BookRepository repository;

    BookController(BookRepository repository){
        this.repository = repository;
    }


    @GetMapping("/books/all/list")
    List<Book> all(){
        return repository.findAll();
    }

    @GetMapping("/books/all/size")
    Long allCount(){
        return repository.count();
    }

    @GetMapping("/books/borrowed/all/list")
    List<Book> allBorrowed(){
        return repository.getBorrowedList();
    }

    @GetMapping("/books/borrowed/all/size")
    Long allBorrowedCount(){
        return repository.getBorrowedCount();
    }

    @GetMapping("/books/borrowed/patron/{patronId}/list")
    List<Book> patronBorrowed(@PathVariable Long patronId){
        return repository.geBorrowedByPatronList(patronId);
    }

    @GetMapping("/books/borrowed/patron/{patronId}/size")
    Long patronBorrowedCount(@PathVariable Long patronId){
        return repository.geBorrowedByPatronCount(patronId);
    }


    @GetMapping("/books/available/list")
    List<Book> available(){
        return repository.geAvailable();
    }


    @GetMapping("/book/{id}")
    Book one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    @PostMapping("/book")
    Book newBookEntity(@RequestBody Book newBook) {
        return repository.save(newBook);
    }

    @PutMapping("/book/{id}")
    Book replaceBookEntity(@RequestBody Book newBook, @PathVariable Long id) {
        return repository.findById(id)
                .map(Book -> {
                    Book.setName(newBook.getName());
                    Book.setCost(newBook.getCost());
                    Book.setTotalQuantity(newBook.getTotalQuantity());
                    Book.setIsan(newBook.getIsan());
                    return repository.save(Book);
                })
                .orElseGet(() -> {
                    newBook.setId(id);
                    return repository.save(newBook);
                });
    }

    @DeleteMapping("/book/{id}")
    void deleteBookEntity(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
