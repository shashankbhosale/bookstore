package com.shashank.app.services.bookstore.patron;


import com.shashank.app.exceptions.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatronController {
    private final PatronRepository repository;

    PatronController(PatronRepository repository){
        this.repository = repository;
    }

    @GetMapping("/patrons")
    List<Patron> all(){
        return repository.findAll();
    }
    @PostMapping("/patron")
    Patron newPatronEntity(@RequestBody Patron newPatron) {
        return repository.save(newPatron);
    }

    @GetMapping("/patron/{id}")
    Patron one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    @PutMapping("/patron/{id}")
    Patron replacePatronRecord(@RequestBody Patron newPatron, @PathVariable Long id) {
        return repository.findById(id)
                .map(Patron -> {
                    Patron.setName(newPatron.getName());
                    return repository.save(Patron);
                })
                .orElseGet(() -> {
                    newPatron.setId(id);
                    return repository.save(newPatron);
                });
    }

    @DeleteMapping("/patron/{id}")
    void deletePatronRecord(@PathVariable Long id) {
        repository.deleteById(id);
    }


}
