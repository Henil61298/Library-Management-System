package com.example.Libmansys.Services;

import com.example.Libmansys.Entities.Author;
import com.example.Libmansys.Entities.Book;
import com.example.Libmansys.Exceptions.InvalidPageCountException;
import com.example.Libmansys.Repository.AuthorRepository;
import com.example.Libmansys.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public String addBook(Book book) throws Exception{
        if (book.getNoOfPages() <= 0){
            throw new InvalidPageCountException("Enter valid number of pages");
        }

        book.setIsIssued(Boolean.FALSE);
        bookRepository.save(book);
        return "Book has been added successfully";
    }

    public String associateBookWithAuthor(Integer bookId, Integer authorId) throws Exception{
        Optional<Book> book = bookRepository.findById(bookId);

        if (book.isEmpty()){
            throw new Exception("Invalid bookId");
        }

        Optional<Author> author = authorRepository.findById(authorId);

        if (author.isEmpty()){
            throw new Exception("Invalid authorId");
        }

        Book book1 = book.get();

        Author author1 = author.get();

        book1.setAuthor(author1);

        author1.setNoOfBooks(author1.getNoOfBooks()+1);

        bookRepository.save(book1);
        authorRepository.save(author1);
        return "Book and author are associated";
    }

    public List<Book> findAllBooksByAuthor(Integer authorId){
        List<Book> bookList = new ArrayList<>();

        List<Book> getAllBooks = bookRepository.findAll();

        for (Book b : getAllBooks){
            if (b.getAuthor().getAuthorId().equals(authorId)){
                bookList.add(b);
            }
        }

        return bookList;
    }
}
