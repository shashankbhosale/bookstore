package com.shashank.app.services.bookstore.patron;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PatronRepository extends JpaRepository<Patron, Long>, JpaSpecificationExecutor<Patron> {

}
