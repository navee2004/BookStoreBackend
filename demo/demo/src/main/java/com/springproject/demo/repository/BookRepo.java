package com.springproject.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springproject.demo.model.BookModel;

@Repository
public interface BookRepo extends JpaRepository<BookModel, Integer> {
    Optional<BookModel> findByEmail(String email);

    void deleteByEmail(String email);
}
