package com.springproject.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springproject.demo.model.BookModel;
import com.springproject.demo.repository.BookRepo;

@Service
public class BookService {
    @Autowired
    private BookRepo bookRepo;

    public BookModel addBook(BookModel bookModel) {
        return bookRepo.save(bookModel);
    }

    public List<BookModel> getAllUsers() {
        return bookRepo.findAll();
    }

    public Optional<BookModel> getEunoiaById(int id) {
        return bookRepo.findById(id);
    }

    public Optional<BookModel> getHealthcareByEmail(String email) {
        return bookRepo.findByEmail(email);
    }

    public String updateUser(BookModel bookModel, int id) {
        if (!bookRepo.existsById(id)) {
            return "User not found";
        }
        bookModel.setId(id);
        bookRepo.save(bookModel);
        return "User updated successfully";
    }

    public String deleteUser(int id) {
        if (!bookRepo.existsById(id)) {
            return "User not found";
        }
        bookRepo.deleteById(id);
        return "User deleted successfully";
    }

    public void deletedemoByEmail(String email) {
        bookRepo.deleteByEmail(email);
    }
}
