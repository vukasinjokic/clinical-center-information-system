package com.example.demo.Repository;

import com.example.demo.model.CodeBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeBookRepository extends JpaRepository<CodeBook, Integer> {

}
