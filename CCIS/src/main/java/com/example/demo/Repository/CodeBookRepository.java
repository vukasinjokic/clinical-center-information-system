package com.example.demo.Repository;

import com.example.demo.model.CodeBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CodeBookRepository extends JpaRepository<CodeBook, Integer> {

}
