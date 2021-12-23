package com.shashank.app.services.bookstore.patron;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shashank.app.services.bookstore.borrow.Borrow;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;


@Entity
@Getter
@Setter
@Table(name = "patron")
public class Patron implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Date dob;
    private String phoneNumber;

    @JsonIgnore
    @OneToMany(
            mappedBy = "patron",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Borrow> borrows = new ArrayList<>();

    public Patron(){}

    public Patron(String name, Date dob, String phoneNumber) {
        this.name = name;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patron that = (Patron) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
