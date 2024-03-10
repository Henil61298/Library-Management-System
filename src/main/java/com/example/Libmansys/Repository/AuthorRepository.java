package com.example.Libmansys.Repository;

import com.example.Libmansys.Entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
