package com.example.Libmansys.Repository;

import com.example.Libmansys.Entities.Author;
import com.example.Libmansys.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
