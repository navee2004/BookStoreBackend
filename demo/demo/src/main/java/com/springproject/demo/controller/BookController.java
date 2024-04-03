package com.springproject.demo.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springproject.demo.model.BookModel;
import com.springproject.demo.service.BookService;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/addbook")
    public BookModel addBook(@RequestBody BookModel bookModel) {
        return bookService.addBook(bookModel);
    }

    @GetMapping("/users")
    public List<BookModel> getAllUsers() {
        List<BookModel> allUsers = bookService.getAllUsers();
        if (allUsers.isEmpty()) {
            return Collections.emptyList(); // Or return an appropriate response
        } else {
            return allUsers;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookModel> getEunoiaById(@PathVariable int id) {
        Optional<BookModel> eunoia = bookService.getEunoiaById(id);
        return eunoia.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<BookModel> getHealthcareByEmail(@PathVariable String email) {
        Optional<BookModel> healthcare = bookService.getHealthcareByEmail(email);
        return healthcare.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<String> updateUser(@RequestBody BookModel bookModel, @PathVariable("id") int id) {
        String result = bookService.updateUser(bookModel, id);
        if (result.equals("User not found")) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
        String result = bookService.deleteUser(id);
        if (result.equals("User not found")) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @DeleteMapping("/email/{email}")
    public ResponseEntity<Void> deleteHealthDataByEmail(@PathVariable String email) {
        bookService.deletedemoByEmail(email);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
