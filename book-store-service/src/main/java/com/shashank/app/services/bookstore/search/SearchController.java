package com.shashank.app.services.bookstore.search;

import com.shashank.app.services.bookstore.book.Book;
import com.shashank.app.services.bookstore.book.BookRepository;
import com.shashank.app.services.bookstore.borrow.Borrow;
import com.shashank.app.services.bookstore.borrow.BorrowRepository;
import com.shashank.app.services.bookstore.patron.Patron;
import com.shashank.app.services.bookstore.patron.PatronRepository;
import com.shashank.app.util.search.SearchUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class SearchController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    PatronRepository patronRepository;

    @Autowired
    BorrowRepository borrowRepository;


    @GetMapping("/search/book")
    List<Book> searchBook(@RequestParam(value = "search") String search){
        log.info("search book: " + search);
        Specification<Book> spec = SearchUtil.getSpecification(search);
        return bookRepository.findAll(spec);
    }

    @GetMapping("/search/patron")
    List<Patron> searchPatron(@RequestParam(value = "search") String search){
        log.info("search patron: " + search);
        Specification<Patron> spec = SearchUtil.getSpecification(search);
        return patronRepository.findAll(spec);
    }



}
