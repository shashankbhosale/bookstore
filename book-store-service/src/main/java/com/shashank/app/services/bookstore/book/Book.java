package com.shashank.app.services.bookstore.book;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shashank.app.services.bookstore.borrow.Borrow;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Getter
@Setter
@Table(name = "book")
public class Book implements Serializable {

    @javax.persistence.Id
    @GeneratedValue
    private Long id;

    private String name;
    private String isan;

    @JsonIgnore
    @OneToMany(
            mappedBy = "book",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Borrow> borrows = new ArrayList<>();


    //cost in USD / default currency
    private double cost;

    private int totalQuantity;

    public Book(){}

    public Book(String name, String isan, int totalQuantity, double cost) {
        this.name = name;
        this.isan = isan;
        this.cost = cost;
        this.totalQuantity = totalQuantity;
    }

    public void addBorrow(Borrow borrow){
        int curBorrows = this.borrows.size();
        if(this.totalQuantity <= curBorrows){
            throw new BookNotAvailableException(this);
        }
        this.borrows.add(borrow);
    }

    public int getBorrowedQuantity(){
        return this.borrows.size();
    }

    public int getAvailableQuantity(){
        return this.totalQuantity - this.borrows.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id.equals(book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
