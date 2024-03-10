package com.example.Libmansys.Services;

import com.example.Libmansys.Entities.Author;
import com.example.Libmansys.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public String addAuthor(Author author){
        authorRepository.save(author);
        author.setNoOfBooks(0);
        return "Author has been added";
    }

    public Author getAuthorWithMaxBooks(){
        Author author = null;
        int maxNumber = 0;

        List<Author> authorList = authorRepository.findAll();
        for (Author author1 : authorList){
            if (author1.getNoOfBooks() > maxNumber){
                maxNumber = author1.getNoOfBooks();
                author = author1;
            }
        }

        return author;
    }
}
