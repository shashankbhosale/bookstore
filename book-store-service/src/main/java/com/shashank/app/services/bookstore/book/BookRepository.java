package com.shashank.app.services.bookstore.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    @Query(value = "SELECT " +
            " COUNT(*) count" +
            " FROM book b " +
            " WHERE id IN (SELECT book_id from borrow) ", nativeQuery = true)
    Long getBorrowedCount();


    @Query(value = "SELECT b.* " +
            " FROM book b " +
            " WHERE id IN (SELECT book_id from borrow) ", nativeQuery = true)
    List<Book> getBorrowedList();


    @Query(value = "SELECT " +
            " count(*) count " +
            " FROM book b " +
            " join borrow bw on b.id = bw.book_id " +
            " join patron p on p.id = bw.patron_id " +
            " WHERE p.id = :patron_id ", nativeQuery = true)
    Long geBorrowedByPatronCount(@Param("patron_id") Long patron_id);


    @Query(value = "SELECT " +
            " b.* " +
            " FROM book b " +
            " join borrow bw on b.id = bw.book_id " +
            " join patron p on p.id = bw.patron_id " +
            " WHERE p.id = :patron_id ", nativeQuery = true)
    List<Book> geBorrowedByPatronList(@Param("patron_id") Long patron_id);


    @Query(value = "SELECT " +
            " b.* " +
            " FROM book b " +
            " WHERE b.total_quantity > (select count(*) from borrow bw where b.id = bw.book_id ) " , nativeQuery = true)
    List<Book> geAvailable();


}
