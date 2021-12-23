package com.shashank.app.services.bookstore.borrow;

import com.shashank.app.services.bookstore.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BorrowRepository extends JpaRepository<Borrow, Long>, JpaSpecificationExecutor<Borrow> {


    @Query(value = "SELECT " +
            " COUNT(*) count" +
            " FROM book b " +
            " WHERE id IN (SELECT book_id from borrow) ", nativeQuery = true)
    Long getBorrowedCount();


    @Query(value = "SELECT b.* " +
            " FROM book b " +
            " WHERE id IN (SELECT book_id from borrow) ", nativeQuery = true)
    List<Book> getBorrowedList();
}
